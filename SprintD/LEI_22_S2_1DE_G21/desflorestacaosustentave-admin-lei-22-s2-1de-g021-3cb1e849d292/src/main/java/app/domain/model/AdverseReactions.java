package app.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AdverseReactions implements Serializable {
    List<String> adverseReactions;

    public AdverseReactions() {
        this.adverseReactions = new ArrayList<>();
    }

    public boolean addAdverseReaction(String adverseReaction) {
        return this.adverseReactions.add(adverseReaction);
    }
}
