import { Component, OnInit } from '@angular/core';
import { ReferenceMaterial } from '@app/_models';
import { Router, ActivatedRoute } from '@angular/router';
import { AlertService, ReferenceMaterialService } from '@app/_services';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';

@Component({
  selector: 'app-reference-material',
  templateUrl: './reference-material.component.html',
  styleUrls: ['./reference-material.component.css']
})
export class ReferenceMaterialComponent implements OnInit {
  referenceMaterialForm: FormGroup;
  referenceMaterials: ReferenceMaterial[] = [];
  referenceMaterial: ReferenceMaterial;
  loading = false;
  submitted = false;
  returnUrl: string;

  constructor(
    private formBuilder: FormBuilder,
    private referenceMaterialService: ReferenceMaterialService,
    private alertService: AlertService,
    private router: Router
    ) { }

  ngOnInit() {
    this.referenceMaterialForm = this.formBuilder.group({
      title: ['', Validators.required],
      resourceLocation: ['', Validators.required]
    });
  }

    // convenience getter for easy access to form fields
    get f() { return this.referenceMaterialForm.controls; }

    onSubmit() {
        this.submitted = true;

        // stop here if form is invalid
        if (this.referenceMaterialForm.invalid) {
            return;
        }

        this.loading = true;
        if (this.referenceMaterialForm.value.id) {
          this.referenceMaterialService
              .create(this.referenceMaterialForm.value)
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
          this.referenceMaterialService
              .update(this.referenceMaterialForm.value)
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
