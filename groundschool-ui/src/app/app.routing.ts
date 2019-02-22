import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { UserComponent } from './user';
import { EventComponent } from './event';
import { LessonComponent } from './lesson';
import { QuestionComponent } from './question';
import { QuizComponent } from './quiz';
import { LessonPlanComponent } from './lesson-plan';
import { ReferenceMaterialComponent } from './reference-material';
import { AuthGuard } from './_guards';

const appRoutes: Routes = [
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'user', component: UserComponent },
    { path: 'event', component: EventComponent },
    { path: 'lesson', component: LessonComponent },
    { path: 'lessonplan', component: LessonPlanComponent },
    { path: 'question', component: QuestionComponent },
    { path: 'quiz', component: QuizComponent },
    { path: 'referencematerial', component: ReferenceMaterialComponent },

    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

export const routing = RouterModule.forRoot(appRoutes);