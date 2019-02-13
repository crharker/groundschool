import { Address } from "./address";

export class Event {
    id: number;
    title: string;
    started: boolean;
    startTime: Date;
    completed: boolean;
    completedTime: Date;
    calendarUrl: string;
    checkinCode: string;
    checkinCodeRequired: boolean;
    address: Address;
    lessonPlanId: number;
}