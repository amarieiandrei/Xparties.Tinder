import { EventCategory } from "./event-category.enum";

export interface Event {
  externalId: string;
  name: string;
  date: Date;
  category: EventCategory
}