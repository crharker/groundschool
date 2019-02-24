import { Component, OnInit } from '@angular/core';
import { Lesson, User } from '@app/_models';
import { LessonService, AuthenticationService } from '@app/_services';

@Component({
  selector: 'app-syllabus',
  templateUrl: './syllabus.component.html',
  styleUrls: ['./syllabus.component.css']
})
export class SyllabusComponent implements OnInit {
  currentUser: User;
  attendedLessons: Lesson[] = [];

  constructor(
    private authenticationService: AuthenticationService,
    private lessonService: LessonService
  ) { }

    ngOnInit() {
      this.currentUser = this.authenticationService.currentUserValue;
      this.lessonService
          .getAllAttendedLessons(this.currentUser.id)
          .subscribe(lessons => {
            this.attendedLessons = lessons;
          });
        }

}
