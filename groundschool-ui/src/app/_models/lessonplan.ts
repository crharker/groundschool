import { Activity } from "./activity";

export class LessonPlan {
    id: number;
    title: string;
    summary: string;
    objective: string;
    content: string;
    schedule: string;
    equipment: string;
    instructorActions: string;
    studentActions: string;
    completionStandards: string;
    activities: Activity[];
}