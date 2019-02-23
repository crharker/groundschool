import { Component, OnInit } from '@angular/core';
import { LessonPlan } from '@app/_models';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService, LessonPlanService } from '@app/_services';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-lesson-plan',
  templateUrl: './lesson-plan.component.html',
  styleUrls: ['./lesson-plan.component.css']
})
export class LessonPlanComponent implements OnInit {
  lessonPlanForm: FormGroup;
  lessonplans: LessonPlan[] = [];
  lessonPlan: LessonPlan;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private lessonPlanService: LessonPlanService,
    private alertService: AlertService,
    private router: Router
    ) { }

  ngOnInit() {
    this.lessonPlanForm = this.formBuilder.group({
      title: ['', Validators.required],
      summary: ['', Validators.required],
      objective: ['', Validators.required],
      content: ['', Validators.required],
      schedule: ['', Validators.required],
      equipment: ['', Validators.required],
      instructorActions: ['', Validators.required],
      studentActions: ['', Validators.required],
      completionStandards: ['', Validators.required],
      activities: ['', Validators.required]
    });
  }

    // convenience getter for easy access to form fields
    get f() { return this.lessonPlanForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.lessonPlanForm.invalid) {
            return;
        }

        this.loading = true;
        if (this.lessonPlanForm.value.id) {
          this.lessonPlanService
              .create(this.lessonPlanForm.value)
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
          this.lessonPlanService
              .update(this.lessonPlanForm.value)
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
