import { Answer } from "./answer";
import { ReferenceMaterial } from "./referencematerial";

export class Question {
    id: number;
    text: string;
    course: string;
    unit: string;
    subUnit: string;
    discussion: string;
    learningStatementCode: string;
    answers: Answer[];
    referenceMaterials: ReferenceMaterial[];
    duration: number;
}