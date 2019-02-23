import { Component, OnInit } from '@angular/core';
import { User } from '@app/_models';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService, UserService } from '@app/_services';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  userForm: FormGroup;
  users: User[] = [];
  user: User;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private alertService: AlertService,
    private router: Router
    ) { }

  ngOnInit() {
    this.userForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      emailVerified: ['', Validators.required],
      emailEnabled: ['', Validators.required],
      sms: ['', Validators.required],
      smsVerified: ['', Validators.required],
      smsEnabled: ['', Validators.required],
      slack: ['', Validators.required],
      slackVerified: ['', Validators.required],
      slackEnabled: ['', Validators.required],
      youthProgramParticipant: ['', Validators.required],
      eaaNumber: ['', Validators.required],
      certificateNumber: ['', Validators.required],
      questionPreference: ['', Validators.required],
      role: ['', Validators.required]
      });
  }

    // convenience getter for easy access to form fields
    get f() { return this.userForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.userForm.invalid) {
            return;
        }

        this.loading = true;
        if (this.userForm.value.id) {
          this.userService
              .create(this.userForm.value)
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
          this.userService
              .update(this.userForm.value)
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
