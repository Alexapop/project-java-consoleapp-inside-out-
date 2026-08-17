package org.factoriaf5.project_inside_out.application.usecase;

import java.io.FileWriter;
import java.io.IOException;

import java.util.List;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;
import org.factoriaf5.project_inside_out.domain.entities.Moment;

public class ExportMomentsToCSVUseCase {
    private MomentRepository momentRepository;

    public ExportMomentsToCSVUseCase(MomentRepository momentRepository) {
        this.momentRepository = momentRepository;

    }

public void execute() throws IOException{
    List<Moment> moments= momentRepository.findAllMoments();

    try (FileWriter writer= new FileWriter("moments.csv")){
    writer.write("title, description, emotion, momentDate\n");
    
    for (Moment moment : moments){
    writer.write(
    moment.getTitle() + "," +
    moment.getDescription() + ","+
    moment.getEmotion() + "," +
    moment.getMomentDate() + "\n"
  );

}
}
}
    }
