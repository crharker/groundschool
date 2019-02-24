import { Component, OnInit } from '@angular/core';
import { Lesson } from '@app/_models';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService, LessonService } from '@app/_services';

@Component({
  selector: 'app-syllabus',
  templateUrl: './syllabus.component.html',
  styleUrls: ['./syllabus.component.css']
})
export class SyllabusComponent implements OnInit {
  lessons: Lesson[] = [];
  lesson: Lesson;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private lessonService: LessonService,
    private alertService: AlertService,
    private router: Router
    ) { }

    ngOnInit() {
    }

}
