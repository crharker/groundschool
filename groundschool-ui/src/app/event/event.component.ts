import { Component, OnInit } from '@angular/core';
import { Event } from '@app/_models';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService, EventService } from '@app/_services';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {
  eventForm: FormGroup;
  events: Event[] = [];
  event: Event;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private eventService: EventService,
    private alertService: AlertService,
    private router: Router
    ) { }

  ngOnInit() {
    this.eventForm = this.formBuilder.group({
      title: ['', Validators.required],
      startTime: ['', Validators.required],
      lessonPlanId: ['', Validators.required],
      address: ['', Validators.required]
    });
  }

    // convenience getter for easy access to form fields
    get f() { return this.eventForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.eventForm.invalid) {
            return;
        }

        this.loading = true;
        if (this.eventForm.value.id) {
          this.eventService
              .create(this.eventForm.value)
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
          this.eventService
              .update(this.eventForm.value)
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
