package com.demo.imageaigenerator24.utils;

import com.demo.imageaigenerator24.model.PromptModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/* loaded from: classes.dex */
public class PromptGenerator {
    private List<PromptModel> promptModels;

    public PromptGenerator() {
        ArrayList arrayList = new ArrayList();
        this.promptModels = arrayList;
        arrayList.add(new PromptModel("Space", new String[]{"A galaxy far, far away...", "Stars shining brightly.", "Planets aligning in harmony.", "A black hole devouring light.", "Astronauts exploring the unknown."}));
        this.promptModels.add(new PromptModel("Nature", new String[]{"A serene waterfall in the forest.", "Mountains touching the sky.", "A silent desert at dusk.", "Birds singing at dawn.", "Waves crashing on a secluded beach."}));
        this.promptModels.add(new PromptModel("Cartoon", new String[]{"A mischievous cat chasing a mouse.", "Superheroes flying over the city.", "A talking car on an adventure.", "Magical creatures in a fairyland.", "A ghost trying to be friendly."}));
        this.promptModels.add(new PromptModel("Animals", new String[]{"A lion ruling its kingdom.", "Dolphins dancing in the ocean.", "A curious monkey causing trouble.", "Eagles soaring above the mountains.", "A rabbit outsmarting a fox."}));
        this.promptModels.add(new PromptModel("Fantasy", new String[]{"A knight facing a fire-breathing dragon.", "Elves guarding an ancient forest.", "A witch brewing a mysterious potion.", "A lost city rising from the sands.", "A spaceship discovering a new world."}));
    }

    public String generateRandomPrompt() {
        return this.promptModels.get(new Random().nextInt(this.promptModels.size())).getRandomPrompt();
    }
}
