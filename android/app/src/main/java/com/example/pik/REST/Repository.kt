package com.example.pik.REST

import android.content.Context
import android.os.AsyncTask.execute
import com.example.pik.ChangePasswordForm
import com.example.pik.CredentialsStore
import com.example.pik.CredentialsStore.password
import com.example.pik.CredentialsStore.username
import com.example.pik.REST.Enum.Status
import com.example.pik.REST.dto.ConsultationSearchForm
import com.example.pik.REST.entity.Consultation
import com.example.pik.REST.entity.User
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.URL
import java.nio.charset.StandardCharsets
import java.security.KeyManagementException
import java.security.KeyStore
import java.security.NoSuchAlgorithmException
import java.security.cert.CertificateFactory
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import java.util.concurrent.Callable
import java.util.concurrent.FutureTask
import javax.net.ssl.*


class Repository(context: Context) {

    private val endpointUrl = "https://192.168.0.11:8443"
//    private val endpointUrl = "https://192.168.43.27:8443"

    init {
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val inputStream = context.resources.openRawResource(com.example.pik.R.raw.pikca)
        val certificate = certificateFactory.generateCertificate(inputStream)
        val tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())
        val ks = KeyStore.getInstance(KeyStore.getDefaultType())
        ks.load(null)
        ks.setCertificateEntry("caCert", certificate)
        tmf.init(ks)
        var sc: SSLContext? = null
        try {
            sc = SSLContext.getInstance("SSL")
        } catch (e: NoSuchAlgorithmException) {
            e.printStackTrace()
        }
        try {
            sc!!.init(null, tmf.trustManagers, java.security.SecureRandom())
        } catch (e: KeyManagementException) {
            e.printStackTrace()
        }
        val validHosts = object : HostnameVerifier {
            override fun verify(arg0: String, arg1: SSLSession): Boolean {
                return true
            }
        }
        // All hosts will be valid
        HttpsURLConnection.setDefaultHostnameVerifier(validHosts)
        HttpsURLConnection.setDefaultSSLSocketFactory(sc!!.socketFactory)
    }

    inline fun <reified T> getItem(uri: String): FutureTask<T?> =
        getItem(T::class.java, uri)

    fun <T> getItem(cls: Class<T>, uri: String): FutureTask<T?> {
        val future = FutureTask(Callable {
            try {
                val url = URL("$endpointUrl$uri")
                val myConnection = url.openConnection() as HttpsURLConnection
                myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1")
                myConnection.requestMethod = "GET"
                myConnection.setRequestProperty("Accept", "application/json")
                val encoded =
                    Base64.getEncoder().encodeToString("$username:$password".toByteArray(StandardCharsets.UTF_8))
                myConnection.setRequestProperty("Authorization", "Basic $encoded")

                if (myConnection.responseCode == 200) {
                    val responseBody = myConnection.inputStream
                    val responseBodyReader = InputStreamReader(responseBody, "UTF-8")
                    val json = responseBodyReader.readText()
                    ObjectMapper().readValue(json, cls)
                } else {
                    null
                }
            } catch (e: Exception) {
                throw e
            }
        })

        execute(future)
        return future
    }

    inline fun <reified T> getList(uri: String): FutureTask<List<T>> =
        getList(T::class.java, uri)

    fun <L> getList(type: Class<L>, uri: String): FutureTask<List<L>> {
        val future = FutureTask(Callable<List<L>> {
            try {
                val url = URL("$endpointUrl$uri")
                val myConnection = url.openConnection() as HttpsURLConnection
                myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1")
                myConnection.requestMethod = "GET"
                myConnection.setRequestProperty("Accept", "application/json")
                val encoded =
                    Base64.getEncoder().encodeToString("$username:$password".toByteArray(StandardCharsets.UTF_8))
                myConnection.setRequestProperty("Authorization", "Basic $encoded")

                if (myConnection.responseCode == 200) {
                    val responseBody = myConnection.inputStream
                    val responseBodyReader = InputStreamReader(responseBody, "UTF-8")
                    val json = responseBodyReader.readText()
                    val objectMapper = ObjectMapper()
                    objectMapper.readValue(
                        json,
                        objectMapper.typeFactory.constructCollectionType(List::class.java, type)
                    )
                } else {
                    listOf()
                }
            } catch (e: Exception) {
                throw e
            }
        })
        execute(future)
        return future
    }

    fun getFreeConsultations(date: Long): FutureTask<List<Consultation>> {
        val future = FutureTask(Callable<List<Consultation>> {
            try {
                val url = URL("$endpointUrl/searchConsultations?username=$username")
                val myConnection = url.openConnection() as HttpsURLConnection
                myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1")
                myConnection.requestMethod = "POST"
                myConnection.setRequestProperty("Accept", "application/json")
                myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                val encoded =
                    Base64.getEncoder().encodeToString("$username:$password".toByteArray(StandardCharsets.UTF_8))
                myConnection.setRequestProperty("Authorization", "Basic $encoded")
                myConnection.doOutput = true
                val wr = DataOutputStream(myConnection.outputStream)
                val localDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault())
                wr.writeBytes(
                    ObjectMapper().writeValueAsString(
                        ConsultationSearchForm().apply {
                            dateStart = localDate
                            dateEnd = localDate.plusDays(1)
                            status = Status.FREE
                        })
                )
                wr.flush()
                wr.close()
                if (myConnection.responseCode == 200) {
                    val responseBody = myConnection.inputStream
                    val responseBodyReader = InputStreamReader(responseBody, "UTF-8")
                    val json = responseBodyReader.readText()
                    val objectMapper = ObjectMapper()
                    objectMapper.readValue(
                        json,
                        objectMapper.typeFactory.constructCollectionType(List::class.java, Consultation::class.java)
                    )
                } else {
                    listOf()
                }
            } catch (e: Exception) {
                throw e
            }
        })
        execute(future)
        return future
    }

    fun reserveConsultation(id: String, username: String): FutureTask<Boolean?> {
        try {
            return getItem("/reserveConsultation?consultationId=$id&username=$username")
        } catch (e: Exception) {
            throw e
        }
    }

    fun cancelConsultation(id: String, username: String): FutureTask<Boolean?> {
        try {
            return getItem("/cancelConsultation?consultationId=$id&username=$username")
        } catch (e: Exception) {
            throw e
        }
    }

    fun getReservedConsultations(username: String): FutureTask<List<Consultation>> {
        val future = FutureTask(Callable<List<Consultation>> {
            try {
                val url = URL("$endpointUrl/searchConsultations?username=$username")
                val myConnection = url.openConnection() as HttpsURLConnection
                myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1")
                myConnection.requestMethod = "POST"
                myConnection.setRequestProperty("Accept", "application/json")
                myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
                val encoded = Base64.getEncoder()
                    .encodeToString("${CredentialsStore.username}:$password".toByteArray(StandardCharsets.UTF_8))
                myConnection.setRequestProperty("Authorization", "Basic $encoded")
                myConnection.doOutput = true
                val wr = DataOutputStream(myConnection.outputStream)
                val searchForm = ConsultationSearchForm().apply {
                    studentUsername = username
                    dateStart = LocalDateTime.now()
                    dateEnd = LocalDateTime.now().plusYears(1)
                }
                val mapper = ObjectMapper()
                wr.writeBytes(mapper.writeValueAsString(searchForm))
                wr.flush()
                wr.close()
                val responseCode = myConnection.responseCode
                if (responseCode == 200) {
                    val responseBody = myConnection.inputStream
                    val responseBodyReader = InputStreamReader(responseBody, "UTF-8")
                    val json = responseBodyReader.readText()
                    val objectMapper = ObjectMapper()
                    objectMapper.readValue<List<Consultation>>(
                        json,
                        objectMapper.typeFactory.constructCollectionType(List::class.java, Consultation::class.java)
                    )
                } else {
                    listOf()
                }

            } catch (e: Exception) {
                throw e
            }
        })
        execute(future)
        return future
    }

    fun register(user: User): Boolean {
        try {
            val url = URL("$endpointUrl/register")
            val myConnection = url.openConnection() as HttpsURLConnection
            myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1")
            myConnection.requestMethod = "POST"
            myConnection.setRequestProperty("Accept", "application/json")
            myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            myConnection.doOutput = true
            val wr = DataOutputStream(myConnection.outputStream)
            wr.writeBytes(ObjectMapper().writeValueAsString(user))
            wr.flush()
            wr.close()
            val responseCode = myConnection.responseCode
            if (responseCode == 200) {
                val `in` = BufferedReader(InputStreamReader(myConnection.inputStream))
                var inputLine = `in`.readLine()
                val response = StringBuffer()

                while (inputLine != null) {
                    inputLine = `in`.readLine()
                    response.append(inputLine)
                }
                `in`.close()
                return true
            }
            return false
        } catch (e: Exception) {
            throw e
        }
    }

    fun login(username: String, password: String): Boolean {
        try {
            val url = URL("$endpointUrl/login")
            val myConnection = url.openConnection() as HttpsURLConnection
            myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1")
            myConnection.requestMethod = "GET"
            myConnection.setRequestProperty("Accept", "application/json")
            myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            val encoded = Base64.getEncoder().encodeToString("$username:$password".toByteArray(StandardCharsets.UTF_8))
            myConnection.setRequestProperty("Authorization", "Basic $encoded")

            val responseCode = myConnection.responseCode
            if (responseCode == 200) {
                val `in` = BufferedReader(InputStreamReader(myConnection.inputStream))
                var inputLine = `in`.readLine()
                val response = StringBuffer()

                while (inputLine != null) {
                    inputLine = `in`.readLine()
                    response.append(inputLine)
                }
                `in`.close()
                println(response.toString())
                return true
            }
            return false
        } catch (e: Exception) {
            throw e
        }
    }

    fun changePassword(changePasswordForm: ChangePasswordForm): Boolean {
        try {
            val url = URL("$endpointUrl/user/changePassword/$username")
            val myConnection = url.openConnection() as HttpsURLConnection
            myConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1")
            myConnection.requestMethod = "POST"
            myConnection.setRequestProperty("Accept", "application/json")
            myConnection.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
            val encoded = Base64.getEncoder().encodeToString("$username:$password".toByteArray(StandardCharsets.UTF_8))
            myConnection.setRequestProperty("Authorization", "Basic $encoded")
            myConnection.doOutput = true
            val wr = DataOutputStream(myConnection.outputStream)
            wr.writeBytes(ObjectMapper().writeValueAsString(changePasswordForm))
            wr.flush()
            wr.close()
            val responseCode = myConnection.responseCode
            if (responseCode == 200) {
                val `in` = BufferedReader(InputStreamReader(myConnection.inputStream))
                var inputLine = `in`.readLine()
                val response = StringBuffer()

                while (inputLine != null) {
                    inputLine = `in`.readLine()
                    response.append(inputLine)
                }
                `in`.close()
                return true
            }
            return false
        } catch (e: Exception) {
            throw e
        }
    }
}