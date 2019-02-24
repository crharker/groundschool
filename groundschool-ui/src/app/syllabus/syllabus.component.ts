import { Component, OnInit } from '@angular/core';
import { Lesson, User } from '@app/_models';
import { Subscription } from 'rxjs';
import { LessonService, AuthenticationService } from '@app/_services';

@Component({
  selector: 'app-syllabus',
  templateUrl: './syllabus.component.html',
  styleUrls: ['./syllabus.component.css']
})
export class SyllabusComponent implements OnInit {
  currentUser: User;
  currentUserSubscription: Subscription;
  attendedLessons: Lesson[] = [];

  constructor(
    private authenticationService: AuthenticationService,
    private lessonService: LessonService
    ) { 
      this.currentUserSubscription = this.authenticationService.currentUser.subscribe(user => {
        this.currentUser = user;
      });
    }

    ngOnInit() {
      this.lessonService
          .getAllAttendedLessons(this.currentUser.id)
          .subscribe(lessons => {
            this.attendedLessons = lessons;
          });
    }

}
