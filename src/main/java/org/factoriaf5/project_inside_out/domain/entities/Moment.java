package org.factoriaf5.project_inside_out.domain.entities;

import java.time.LocalDate;

public class Moment {

   private Long id;
   private String title;
   private String description;
   private Emotion emotion;
   private LocalDate momentDate;
   private LocalDate creationDate;
   private LocalDate modificationDate;

   public Moment(Long id, String title, String description, Emotion emotion, LocalDate momentDate, LocalDate creationDate,
         LocalDate modificationDate) {

      this.id = id;
      this.title = title;
      this.description = description;
      this.emotion = emotion;
      this.momentDate = momentDate;
      this.creationDate = creationDate;
      this.modificationDate = modificationDate;

   }

   public Long getId() {
      return id;
   }

   public String getTitle() {
      return title;
   }

   public String getDescription() {
      return description;
   }

   public Emotion getEmotion() {
      return emotion;
   }

   public LocalDate getMomentDate() {
      return momentDate;
   }

   public LocalDate getCreationDate() {
      return creationDate;
   }

   public LocalDate getModificationDate() {
      return modificationDate;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public void setDescription(String description) {
      this.description = description;
   }

   public void setEmotion(Emotion emotion) {
      this.emotion = emotion;
   }

   public void setMomentDate(LocalDate momentDate) {
      this.momentDate = momentDate;
   }
}
