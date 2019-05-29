export class Consultation {
  id: string;
  tutor: string;
  student: string;
  date: Date;
  consultationStartTime: Date;
  consultationEndTime: Date;
  room: number;
  status: string;
}

export class SearchConsultation {
	tutorUsername: string;
	studentUsername: string;
	dateStart: Date;
	dateEnd: Date;
	status: string;
}
