import { Component, OnInit } from '@angular/core';
import { Lesson } from '@app/_models';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService, LessonService } from '@app/_services';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-lesson',
  templateUrl: './lesson.component.html',
  styleUrls: ['./lesson.component.css']
})
export class LessonComponent implements OnInit {
  lessonForm: FormGroup;
  lessons: Lesson[] = [];
  lesson: Lesson;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private lessonService: LessonService,
    private alertService: AlertService,
    private router: Router
    ) { }

  ngOnInit() {
    this.lessonForm = this.formBuilder.group({
      course: ['', Validators.required],
      unit: ['', Validators.required],
      subUnit: ['', Validators.required],
      text: ['', Validators.required]
    });
  }

    // convenience getter for easy access to form fields
    get f() { return this.lessonForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.lessonForm.invalid) {
            return;
        }

        this.loading = true;
        if (this.lessonForm.value.id) {
          this.lessonService
              .create(this.lessonForm.value)
              .pipe(first())
              .subscribe(
                  data => {
                      this.router.navigate([this.returnUrl]);
                  },
                  error => {
                      this.alertService.error(error);
                      this.loading = false;
                  }
          );
        } else {
          this.lessonService
              .update(this.lessonForm.value)
              .pipe(first())
              .subscribe(
                  data => {
                      this.router.navigate([this.returnUrl]);
                  },
                  error => {
                      this.alertService.error(error);
                      this.loading = false;
                  }
          );
        }
    }
}
