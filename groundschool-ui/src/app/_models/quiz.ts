import { Question } from "./question";

export class Quiz {
    id: number;
    title: string;
    started: boolean;
    startTime: Date;
    completed: boolean;
    completedTime: Date;
    questions: Question[];
    lessonPlanId: number;
    quizType: string;
}