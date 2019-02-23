import { Component, OnInit } from '@angular/core';
import { Question } from '@app/_models';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService, QuestionService } from '@app/_services';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {
  questionForm: FormGroup;
  questions: Question[] = [];
  question: Question;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private questionService: QuestionService,
    private alertService: AlertService,
    private router: Router
    ) { }

  ngOnInit() {
    this.questionForm = this.formBuilder.group({
      course: ['', Validators.required],
      unit: ['', Validators.required],
      subUnit: ['', Validators.required],
      discussion: ['', Validators.required],
      learningStatementCode: ['', Validators.required],
      duration: ['', Validators.required],
      answers: ['', Validators.required],
      referenceMaterials: ['', Validators.required],
      text: ['', Validators.required]
    });
  }

    // convenience getter for easy access to form fields
    get f() { return this.questionForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.questionForm.invalid) {
            return;
        }

        this.loading = true;
        if (this.questionForm.value.id) {
          this.questionService
              .create(this.questionForm.value)
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
          this.questionService
              .update(this.questionForm.value)
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
