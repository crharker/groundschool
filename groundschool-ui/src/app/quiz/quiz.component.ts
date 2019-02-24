import { Component, OnInit } from '@angular/core';
import { Quiz } from '@app/_models';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService, QuizService } from '@app/_services';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.css']
})
export class QuizComponent implements OnInit {
  quizForm: FormGroup;
  quizzes: Quiz[] = [];
  quiz: Quiz;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private quizService: QuizService,
    private alertService: AlertService,
    private router: Router
    ) { }

  ngOnInit() {
    this.quizForm = this.formBuilder.group({
      title: ['', Validators.required],
      lessonPlanId: ['', Validators.required],
      questions: ['', Validators.required],
      quizType: ['', Validators.required]
    });
  }

    // convenience getter for easy access to form fields
    get f() { return this.quizForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.quizForm.invalid) {
            return;
        }

        this.loading = true;
        if (this.quizForm.value.id) {
          this.quizService
              .create(this.quizForm.value)
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
          this.quizService
              .update(this.quizForm.value)
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
