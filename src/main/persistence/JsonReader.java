package persistence;

// modelled after JsonSerializationDemo example

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads TeamList from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads TeamList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public TeamList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTeamList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses TeamList from JSON object and returns it
    private TeamList parseTeamList(JSONObject jsonObject) {
        TeamList tl = new TeamList();
        addTeams(tl, jsonObject);
        return tl;
    }

    // MODIFIES: tl
    // EFFECTS: parses teams from JSON object and adds them to teamlist
    private void addTeams(TeamList tl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("teams");
        for (Object json : jsonArray) {
            JSONObject nextTeam = (JSONObject) json;
            addTeam(tl, nextTeam);
        }
    }

    // MODIFIES: tl
    // EFFECTS: parses team from JSON object and adds it to teamlist
    private void addTeam(TeamList tl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Team team = new Team(name);
        addMembers(team, jsonObject);
        if (jsonObject.getBoolean("favourite")) {
            team.changeFavourite();
        }
        tl.addTeam(team);
    }

    // MODIFIES: team
    // EFFECTS: parses members from JSON object and adds it to team
    private void addMembers(Team team, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("members");
        for (Object json : jsonArray) {
            JSONObject nextMember = (JSONObject) json;
            addMember(team, nextMember);
        }
    }

    // MODIFIES: team
    // EFFECTS: parses a member from JSON object and adds it to team
    private void addMember(Team team, JSONObject jsonObject) {
        String givenname = jsonObject.getString("givenname");
        int level = jsonObject.getInt("level");
        HeroType heroType = addHeroType(jsonObject.getJSONObject("herotype"));
        Hero hero = new Hero(givenname, heroType);
        hero.setHeroLevel(level);
        team.addHeroToTeam(hero);
    }

    // EFFECTS: parses hero type from JSON object and returns it
    public HeroType addHeroType(JSONObject jsonObject) {
        String typename = jsonObject.getString("typename");
        List<Skill> skills = addSkills(jsonObject);
        return new HeroType(typename, skills);
    }

    // EFFECTS: parses skills from JSON object and returns it
    public List<Skill> addSkills(JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("skills");
        List<Skill> skills = new ArrayList<>();
        for (Object json : jsonArray) {
            JSONObject nextSkill = (JSONObject) json;
            skills.add(addSkill(nextSkill));
        }
        return skills;
    }

    // EFFECTS: parses skill from JSON object and returns it
    public Skill addSkill(JSONObject jsonObject) {
        String desc = jsonObject.getString("desc");
        JSONArray jsonArray = jsonObject.getJSONArray("stats");
        Skill skill = new Skill(desc);
        for (Object json : jsonArray) {
            JSONObject nextStat = (JSONObject) json;
            skill.addSkillStat(addStat(nextStat));
        }
        return skill;
    }

    // EFFECTS: parses stat from JSON object and returns it
    public Stat addStat(JSONObject jsonObject) {
        String statname = jsonObject.getString("statname");
        int statchance = jsonObject.getInt("statchance");
        Stat stat =  new Stat(statname, statchance);
        return stat;
    }
}