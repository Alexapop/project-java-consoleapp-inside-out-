package org.factoriaf5.project_inside_out.infrastructure.repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.factoriaf5.project_inside_out.domain.entities.Emotion;
import org.factoriaf5.project_inside_out.domain.entities.Moment;
import org.factoriaf5.project_inside_out.domain.repository.MomentRepository;

public class InMemoryMomentRepository implements MomentRepository {
    private final Map<Long, Moment> moments = new LinkedHashMap<Long, Moment>();
    private long nextId = 1L;

   @Override
public Moment add(Moment moment) {
    Long generatedId = nextId++;

    Moment savedMoment = new Moment(
        generatedId,
        moment.getTitle(),
        moment.getDescription(),
        moment.getEmotion(),
        moment.getMomentDate(),
        moment.getCreationDate(),
        moment.getModificationDate()
    );

    moments.put(generatedId, savedMoment);

    return savedMoment;
}

    @Override
    public Moment findById(Long id) {
        Moment moment = moments.get(id);

        if (moment == null) {
            throw new IllegalArgumentException("Moment not found");
        }

        return moment;
    }

    @Override
    public Moment modify(Moment moment) {
        if (!moments.containsKey(moment.getId())) {
            throw new IllegalArgumentException("Moment not found");
        }

        moments.put(moment.getId(), moment);
        return moment;
    }

    @Override
    public List<Moment> findAllMoments() {
        return new ArrayList<>(moments.values());
    }

    @Override
    public void delete(Long id) {
        moments.remove(id);
    }

    @Override
    public List<Moment> filterByEmotion(Emotion emotion) {
        List<Moment> filteredMoments = new ArrayList<>();

        for (Moment moment : moments.values()) {
            if (moment.getEmotion() == emotion) {
                filteredMoments.add(moment);
            }
        }

        return filteredMoments;
    }

    @Override
    public List<Moment> filterByMonth(int month, int year) {
        List<Moment> filteredMoments = new ArrayList<>();

        for (Moment moment : moments.values()) {
            if (moment.getMomentDate().getMonthValue() == month
                    && moment.getMomentDate().getYear() == year) {
                filteredMoments.add(moment);
            }
        }

        return filteredMoments;
    }

}
