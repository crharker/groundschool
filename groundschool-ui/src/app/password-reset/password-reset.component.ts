import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlertService, UserService } from '@app/_services';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-password-reset',
  templateUrl: './password-reset.component.html',
  styleUrls: ['./password-reset.component.css']
})
export class PasswordResetComponent implements OnInit {
  passwordResetForm: FormGroup;
  code: string;
  userId: number;
  loading = false;
  submitted = false;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService,
    private alertService: AlertService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    this.route.queryParams
      .subscribe(params => {
        this.userId = params.userId;
        this.code = params.code;
      });
    this.passwordResetForm = this.formBuilder.group({
      userId: [this.userId, Validators.required],
      code: [this.code, Validators.required],
      password: ['', [Validators.required]]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.passwordResetForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.passwordResetForm.invalid) {
        return;
    }

    this.loading = true;
    this.userService.updatePassword(
      this.passwordResetForm.value.userId, 
      this.passwordResetForm.value.password, 
      this.passwordResetForm.value.code)
        .pipe(first())
        .subscribe(
            data => {
                this.alertService.success('Your password has been reset', true);
                this.router.navigate(['/login']);
            },
            error => {
                this.alertService.error(error);
                this.loading = false;
            });
  }
  
}
