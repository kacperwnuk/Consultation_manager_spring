docker build -t pik .
docker stop pik || true
docker run -d --name=pik --rm -p 8888:8080 pik