package me.skywang.situatedDialogue;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.QDecoderStream;
import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;

public class situatedDialogue extends JavaPlugin {
    // VARIABLE INITIALIZATION
    public static boolean contains(final int[] arr, final int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    // Important
    boolean sharedKnowledge = false; // TODO: CHANGE TO READ FROM CONFIG FILE ok this isn't really used here
    // Abilities are implemented via tools. The ability to share tools is controlled by noItemDropping (boolean) below.

    // Miscellaneous
    Random random = new Random();

    // MYSQL connection variables
    static Connection connection;
    static String mysql_user, mysql_password, mysql_url;

    // Player-specific Variables
    // INVARIANT: Player 1 Variables should correspond exactly with Player 2 Variables
    // Player 1
    UUID player1_UUID = null;
    List<ItemStack> playerOneInventory = new ArrayList<>(); // TODO: Currently unused
    double[] player1_spawn = new double[]{-227.5485678659068, 4.0, 222.30400359751192};
    // Player 2
    UUID player2_UUID = null;
    List<ItemStack> playerTwoInventory = new ArrayList<>(); // TODO: Currently unused
    double[] player2_spawn = player1_spawn; // Same as player1

    // Environment Variables
    // Permissions
    Boolean freeze = true; // Freeze players until both join
    Boolean noItemDropping = true; // No Item Dropping?
    // Build/Break
    ArrayList<Material> tools; // Tools to break materials
    ArrayList<Material> materials; // Materials
    ArrayList<Material> mines = new ArrayList<>(); // Mines
    // Action schema
    ArrayList<int[]> combine_actions = new ArrayList<>(); // [mat_bottom, mat_top, outputmat]
    ArrayList<int[]> move_actions = new ArrayList<>(); // [mat, tool]
    ArrayList<int[]> mine_actions = new ArrayList<>(); // [mine, mat]
    // Player Knowledge of Actions
    ArrayList<int[]> combine_actions_kp1 = new ArrayList<>(); // Same schema as above.
    ArrayList<int[]> move_actions_kp1 = new ArrayList<>();
    ArrayList<int[]> combine_actions_kp2 = new ArrayList<>();
    ArrayList<int[]> move_actions_kp2 = new ArrayList<>();
    // Maximum final state objects are 2x2x5 (r x c x h)
    Material finalState;// 1 material only
    //Material[][][][] finalStates = new Material[4][2][2][5]; // [indexoffinalstateobject][row][column][height]
    //int finalState_numMaterials = 2; // TODO: CHANGE TO READ FROM CONFIG FILE AND CONNECT PYTHON SCRIPT IN BASH
    // State checking
    ArrayList<Integer> materials_neededForCompletion = new ArrayList<>();
    // Interaction space; Bounds are INCLUSIVE
    int[][] gameBoundaries = {
            {-239, -219}, // x, [lb, ub]
            {4, 23}, // y, [lb, ub]
            {212, 232}  // z, [lb, ub]
    };
    // Stadium locations
    int[][] stadiumBlockLocations = {
            // x, y, z
            {-219, 4, 232},{-220, 4, 232},{-221, 4, 232},{-222, 4, 232},
            {-219, 4, 231},{-220, 4, 231},{-221, 4, 231},{-222, 4, 231},
            {-219, 4, 230},{-220, 4, 230},{-221, 4, 230},{-222, 4, 230},
            {-219, 4, 229},{-220, 4, 229},{-221, 4, 229},{-222, 4, 229},

            {-219, 4, 215},{-220, 4, 215},{-221, 4, 215},{-222, 4, 215},
            {-219, 4, 214},{-220, 4, 214},{-221, 4, 214},{-222, 4, 214},
            {-219, 4, 213},{-220, 4, 213},{-221, 4, 213},{-222, 4, 213},
            {-219, 4, 212},{-220, 4, 212},{-221, 4, 212},{-222, 4, 212},

            {-236, 4, 215},{-237, 4, 215},{-238, 4, 215},{-239, 4, 215},
            {-236, 4, 214},{-237, 4, 214},{-238, 4, 214},{-239, 4, 214},
            {-236, 4, 213},{-237, 4, 213},{-238, 4, 213},{-239, 4, 213},
            {-236, 4, 212},{-237, 4, 212},{-238, 4, 212},{-239, 4, 212},

            {-236, 4, 232},{-237, 4, 232},{-238, 4, 232},{-239, 4, 232},
            {-236, 4, 231},{-237, 4, 231},{-238, 4, 231},{-239, 4, 231},
            {-236, 4, 230},{-237, 4, 230},{-238, 4, 230},{-239, 4, 230},
            {-236, 4, 229},{-237, 4, 229},{-238, 4, 229},{-239, 4, 229}
    };
    // Mine spawn locations
    int[][] mineSpawnLocations = {
            // x, y, z
            {-239, 4, 227}, {-239, 4, 226}, {-239, 4, 225}, {-239, 4, 224}, {-239, 4, 223}, {-239, 4, 222}, {-239, 4, 221}, {-239, 4, 220}, {-239, 4, 219}, {-239, 4, 218}, {-239, 4, 217},
            {-219, 4, 227}, {-219, 4, 226}, {-219, 4, 225}, {-219, 4, 224}, {-219, 4, 223}, {-219, 4, 222}, {-219, 4, 221}, {-219, 4, 220}, {-219, 4, 219}, {-219, 4, 218}, {-219, 4, 217},
            {-224, 4, 232}, {-225, 4, 232}, {-226, 4, 232}, {-227, 4, 232}, {-228, 4, 232}, {-229, 4, 232}, {-230, 4, 232}, {-231, 4, 232}, {-232, 4, 232}, {-233, 4, 232}, {-234, 4, 232},
            {-224, 4, 212}, {-225, 4, 212}, {-226, 4, 212}, {-227, 4, 212}, {-228, 4, 212}, {-229, 4, 212}, {-230, 4, 212}, {-231, 4, 212}, {-232, 4, 212}, {-233, 4, 212}, {-234, 4, 212}
    };
    // Player Starting Inventories
    ArrayList<Material> player1_starting_inventory = new ArrayList<>();
    ArrayList<Material> player2_starting_inventory = new ArrayList<>();

    // Configuration File
    FileConfiguration config = getConfig();

    // Others
    // Default set of "good tools"
    ArrayList<Material> global_tools = new ArrayList<>();
    // Default set of "good materials"
    ArrayList<Material> global_materials = new ArrayList<>();
    // Default set of "good mines"
    ArrayList<Material> global_mines = new ArrayList<>();
    // Convenient variables
    long logFrequency_ticks = 100;

    @Override
    public void onEnable() {
        // Configuration File Attributes
        config.addDefault("logFrequency_ticks", 100);           // Logging Frequency
        config.addDefault("mysql_host", "localhost");           // MySQL
        config.addDefault("mysql_port", 3306);                  // .
        config.addDefault("mysql_database", "minecraft");       // .
        config.addDefault("mysql_requireSSL", false);           // .
        config.addDefault("mysql_user", "root");                // .
        config.addDefault("mysql_password", "password");  // .
        config.options().copyDefaults(true);                                // DEFAULT
        saveConfig();                                                       // DEFAULT

        // Listener for player actions
        new PluginListener(this);

        // Global variable setup
        logFrequency_ticks = config.getLong("logFrequency_ticks");

        // MYSQL Connection Setup
        mysql_user = config.getString("mysql_user");
        mysql_password = config.getString("mysql_password");
        mysql_url = "jdbc:mysql://" + config.getString("mysql_host") + ":" + Integer.toString(config.getInt("mysql_port")) + "/" + config.getString("mysql_database");
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.err.println("jdbc driver unavailable!");
            return;
        }
        try {
            connection = DriverManager.getConnection(mysql_url, mysql_user, mysql_password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> uniqueTools = new ArrayList<>();
        // Plan Setup
        initializeGlobalMats();
        try {
            JSONParser parser = new JSONParser();
            JSONObject plan_all = (JSONObject) parser.parse(new FileReader("plan.json"));

            // First, get the full plan to initialize global stuff.
            JSONArray actions_JSON = (JSONArray) plan_all.get("full");
            // Initialize global arrays
//            ArrayList<Integer> uniqueTools = new ArrayList<>();
            for (int i = 0; i < actions_JSON.size(); i++) {
                JSONObject action = (JSONObject) actions_JSON.get(i);
                ArrayList<Long> tools = (ArrayList<Long>) action.get("tools");
                for (int j = 0; j < tools.size(); j++) {
                    if (!uniqueTools.contains(tools.get(j).intValue())) {
                        uniqueTools.add(tools.get(j).intValue());
                    }
                }
                // Global arrays: action schemas
                ArrayList<ArrayList<Long>> make = (ArrayList<ArrayList<Long>>) action.get("make");
                if (make.size() != 0) {
                    // Combine action?
                    // Multiple possible combinations
                    for (int j = 0; j < make.size(); j++) {
                        int[] combine_action = new int[3];
                        combine_action[0] = make.get(j).get(0).intValue();
                        combine_action[1] = make.get(j).get(1).intValue();
                        combine_action[2] = i;
                        combine_actions.add(combine_action);
                    }
                    // Invariant: if it's something that can be combined, then it must not be something that is mined
                    for (int j = 0; j < tools.size(); j++) {
                        int[] move_action = new int[2];
                        move_action[0] = i;
                        move_action[1] = tools.get(j).intValue();
                        move_actions.add(move_action);
                    }
                } else {
                    // Mine action
                    for (int j = 0; j < tools.size(); j++) {
                        // Destroy
                        int[] move_action = new int[2];
                        move_action[0] = i;
                        move_action[1] = tools.get(j).intValue();
                        move_actions.add(move_action);
                        // Mine
                        int[] mine_action = new int[2];
                        mine_action[0] = mines.size();
                        mines.add(global_mines.get(mines.size()));
                        mine_action[1] = i;
                        mine_actions.add(mine_action);
                    }
                }
            }
            // Global arrays: tools and materials
            tools = new ArrayList<>(uniqueTools.size());
            materials = new ArrayList<>(actions_JSON.size());
            // First n of global_tools and global_materials
            for (int i = 0; i < uniqueTools.size(); i++) {
                tools.add(global_tools.get(i));
            }
            for (int i = 0; i < actions_JSON.size(); i++) {
                materials.add(global_materials.get(i));
            }
            // materials_neededForCompletion
            for (int i = 0; i < materials.size(); i++) {
                materials_neededForCompletion.add(0);
            }


            // Disparate Knowledge is below
            // This is a *very* shitty implementation (lots of duplicated code from above) but it's an easy solution if we want to generate plans from plan_generator.py as opposed to here.

            // Initialize Plan Knowledge for Player1
            actions_JSON = (JSONArray) plan_all.get("player1");
            for (int i = 0; i < actions_JSON.size(); i++) {
                JSONObject action = (JSONObject) actions_JSON.get(i);
                ArrayList<Long> tools = (ArrayList<Long>) action.get("tools");
                ArrayList<ArrayList<Long>> make = (ArrayList<ArrayList<Long>>) action.get("make");
                if (make.size() != 0) {
                    for (int j = 0; j < make.size(); j++) {
                        int[] combine_action = new int[3]; combine_action[0] = make.get(j).get(0).intValue(); combine_action[1] = make.get(j).get(1).intValue(); combine_action[2] = i;
                        combine_actions_kp1.add(combine_action);
                    }
                    for (int j = 0; j < tools.size(); j++) {
                        int[] move_action = new int[2]; move_action[0] = i; move_action[1] = tools.get(j).intValue();
                        move_actions_kp1.add(move_action);
                    }
                } else {
                    for (int j = 0; j < tools.size(); j++) {
                        int[] move_action = new int[2]; move_action[0] = i; move_action[1] = tools.get(j).intValue();
                        move_actions_kp1.add(move_action);
                    }
                }
            }
            // Initialize Plan Knowledge for Player2
            actions_JSON = (JSONArray) plan_all.get("player2");
            for (int i = 0; i < actions_JSON.size(); i++) {
                JSONObject action = (JSONObject) actions_JSON.get(i);
                ArrayList<Long> tools = (ArrayList<Long>) action.get("tools");
                ArrayList<ArrayList<Long>> make = (ArrayList<ArrayList<Long>>) action.get("make");
                if (make.size() != 0) {
                    for (int j = 0; j < make.size(); j++) {
                        int[] combine_action = new int[3]; combine_action[0] = make.get(j).get(0).intValue(); combine_action[1] = make.get(j).get(1).intValue(); combine_action[2] = i;
                        combine_actions_kp2.add(combine_action);
                    }
                    for (int j = 0; j < tools.size(); j++) {
                        int[] move_action = new int[2]; move_action[0] = i; move_action[1] = tools.get(j).intValue();
                        move_actions_kp2.add(move_action);
                    }
                } else {
                    for (int j = 0; j < tools.size(); j++) {
                        int[] move_action = new int[2]; move_action[0] = i; move_action[1] = tools.get(j).intValue();
                        move_actions_kp2.add(move_action);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO: Disparate / Same Skills Specification NOT YET IMPLEMENTED IN PLAN_GENERATOR
        // Player Inventories Initialization
        // Players must have at least 1 tool. The tools they hold can be redundant.
        // For simplicity, players will have the first tool in the "tools" arraylist, player2 the second, others randomly.

        if (uniqueTools.size() == 1) {
            player1_starting_inventory.add(tools.get(0));
            player2_starting_inventory.add(tools.get(0));
        }
        else
        {
            player1_starting_inventory.add(tools.get(0));
            player2_starting_inventory.add(tools.get(1));
        }
        // Other tools will be at random. 0 = player1 only, 1 = player2 only, 2 = both players get it.
        for (int i = 2; i < tools.size(); i++) {
            int seed = random.nextInt(3);
            if (seed == 0) {
                player1_starting_inventory.add(tools.get(i));
            } else if (seed == 1) {
                player2_starting_inventory.add(tools.get(i));
            } else {
                player1_starting_inventory.add(tools.get(i));
                player2_starting_inventory.add(tools.get(i));
            }
        }

        // Final state setup
        finalState = materials.get(0);
        /*
        // Material[][][][] finalStates = new Material[4][2][2][5];
        for (int state_index = 0; state_index < finalStates.length; state_index++) {
            for (int row_index = 0; row_index < finalStates[0].length; row_index++) {
                for (int column_index = 0; column_index < finalStates[0][0].length; column_index++) {
                    for (int height_index = 0; height_index < finalStates[0][0][0].length; height_index++) {

                        // Must have at least 4 states. [0][0][0] of each state will have a material.
                        if (row_index == 0 && column_index == 0 && height_index == 0) {
                            finalStates[state_index][row_index][column_index][height_index] =
                                    materials.get(random.nextInt(finalState_numMaterials));
                            continue;
                        }

                        int placeMaterialHere = random.nextInt(2); // 0 = no place, 1 = place
                        if (height_index == 0) { // For base, simply randomly 50/50 choose if we want a material there.
                            if (placeMaterialHere == 1) {
                                finalStates[state_index][row_index][column_index][height_index] =
                                        materials.get(random.nextInt(finalState_numMaterials));
                            }
                            continue;
                        } else { // If not base, only place material if there is a material underneath & randomly chosen.
                            if (placeMaterialHere == 1) {
                                if (finalStates[state_index][row_index][column_index][height_index-1] != null) {
                                    finalStates[state_index][row_index][column_index][height_index] =
                                            materials.get(random.nextInt(finalState_numMaterials));
                                }
                            }
                            continue;
                        }

                    }
                }
            }
        }*/

        // Materials needed for Completion Initialization
        ArrayList<Integer> queue = new ArrayList<Integer>();
        queue.add(0);

        while (queue.size() != 0) {
            int mat_index = queue.remove(0);
            materials_neededForCompletion.set(mat_index, materials_neededForCompletion.get(mat_index) + 1);
            for (int[] combine_action : combine_actions) {
                if (combine_action[2] == mat_index) {
                    queue.add(combine_action[0]);
                    queue.add(combine_action[1]);
                }
            }
        }

        // WRITE TO HTML (Materials needed for Completion)
        try {

            String p1_html = FileUtils.readFileToString(new File("../mean/dist/player1_template.html"));
            String p2_html = FileUtils.readFileToString(new File("../mean/dist/player2_template.html"));

            String mats = "";

            int mat_index = 0;
            for (int material_count : materials_neededForCompletion) {
                // So we do this instead of looping through material_count because in a previous version of the code we displayed the number of materials needed for the final product. Not needed here, I'll just keep it.
                mats += "'" + materials.get(mat_index).name() + "',";
                mat_index++;
            }
            // Remove final trailing comma
            mats = mats.substring(0, mats.length() - 1);

            p1_html = p1_html.replace("/*$MATS*/", mats);
            p2_html = p2_html.replace("/*$MATS*/", mats);


            File p1_htmlFile = new File("../mean/dist/player1.html");
            FileUtils.writeStringToFile(p1_htmlFile, p1_html);
            File p2_htmlFile = new File("../mean/dist/player2.html");
            FileUtils.writeStringToFile(p2_htmlFile, p2_html);

        } catch (IOException e) {
            e.printStackTrace();
        }

        // WRITE TO JS (graph of knowledge)
        try {
            File template_js = new File("../mean/dist/code_template.js");

            String p1_js = FileUtils.readFileToString(template_js);
            String p2_js = FileUtils.readFileToString(template_js);

            String common_nodes = "";
            String common_selectors = "";
            String common_edges = ""; String p1_edges = ""; String p2_edges = "";

            // Material nodes
            int mat_iter_index = 0;
            for (Material material : materials) {
                String brokenWith = "UNKNOWN"; // Default unknown
                for (int[] mat_tool : move_actions) {
                    if (mat_tool[0] == mat_iter_index) {
                        brokenWith = tools.get(mat_tool[1]).name();
                    }
                }
                // CLEAR FINAL STATE INDICATION: Recall that final state material = index 0
                if (mat_iter_index == 0) { // If final state: state completed = green border
                    common_nodes += "{ data: { id: '" + material.name() + "', label: '" + material.name() + " | " + brokenWith + " | GOAL MATERIAL' }, classes: ['completed', 'outline'] },";
                } else { // Else
                    common_nodes += "{ data: { id: '" + material.name() + "', label: '" + material.name() + " | " + brokenWith + "' }, classes: ['none-working', 'outline'] },";
                }
                common_selectors += ".selector('#" + material.name() + "').css({'background-image':'img/materials/" + material.name() + "'})";
                mat_iter_index++;
            }
            // Mine nodes
            for (Material material : mines) {
                common_nodes += "{ data: { id: '" + material.name() + "', label: '" + material.name() + " | ANY TOOL' }, classes: ['tool', 'outline'] },";
                common_selectors += ".selector('#" + material.name() + "').css({'background-image':'img/materials/" + material.name() + "'})";
            }
            // Mining Actions // [mine, mat]
            for (int[] mine_action : mine_actions) {
                if (!contains(mine_action, -1)) {
                    Material mine = mines.get(mine_action[0]);
                    Material material = materials.get(mine_action[1]);
                    common_edges += "{ data: { source: '" + mine.name() + "', target: '" + material.name() + "' } },";
                }
            }
            // Combining Actions
            for (int[] action : combine_actions_kp1) {
                if (!contains(action, -1)) {
                    Material material_bottom = materials.get(action[0]);
                    Material material_top = materials.get(action[1]);
                    Material material_output = materials.get(action[2]);
                    p1_edges += "{ data: { source: '" + material_bottom.name() + "', target: '" + material_output.name() + "' } },";
                    p1_edges += "{ data: { source: '" + material_top.name() + "', target: '" + material_output.name() + "' } },";
                }
            }
            for (int[] action : combine_actions_kp2) {
                if (!contains(action, -1)) {
                    Material material_bottom = materials.get(action[0]);
                    Material material_top = materials.get(action[1]);
                    Material material_output = materials.get(action[2]);
                    p2_edges += "{ data: { source: '" + material_bottom.name() + "', target: '" + material_output.name() + "' } },";
                    p2_edges += "{ data: { source: '" + material_top.name() + "', target: '" + material_output.name() + "' } },";
                }
            }

            common_nodes = common_nodes.substring(0, common_nodes.length() - 1);
            p1_edges = p1_edges.substring(0, p1_edges.length() - 1);
            p2_edges = p2_edges.substring(0, p2_edges.length() - 1);

            p1_js = p1_js.replace("//$selectors", common_selectors);
            p1_js = p1_js.replace("//$nodes", common_nodes);
            p1_js = p1_js.replace("//$edges", common_edges + p1_edges);
            p2_js = p2_js.replace("//$selectors", common_selectors);
            p2_js = p2_js.replace("//$nodes", common_nodes);
            p2_js = p2_js.replace("//$edges", common_edges + p2_edges);

            File p1_jsFile = new File("../mean/dist/code_player1.js");
            FileUtils.writeStringToFile(p1_jsFile, p1_js);
            File p2_jsFile = new File("../mean/dist/code_player2.js");
            FileUtils.writeStringToFile(p2_jsFile, p2_js);
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        System.out.println(materials_neededForCompletion);
        System.out.println("COMBINE ACTIONS");
        for (int i = 0; i < combine_actions.size(); i++) {
            System.out.println("\n");
            System.out.println(materials.get(combine_actions.get(i)[0]).name());
            System.out.println(materials.get(combine_actions.get(i)[1]).name());
            System.out.println(materials.get(combine_actions.get(i)[2]).name());
        }
        System.out.println("DESTROY ACTIONS");
        for (int i = 0; i < destroy_actions.size(); i++) {
            System.out.println("\n");
            System.out.println(materials.get(destroy_actions.get(i)[0]).name());
            System.out.println(tools.get(destroy_actions.get(i)[1]).name());
        }
        System.out.println("MINE ACTIONS");
        for (int i = 0; i < mine_actions.size(); i++) {
            System.out.println("\n");
            System.out.println(mines.get(mine_actions.get(i)[0]).name());
            System.out.println(materials.get(mine_actions.get(i)[1]).name());
        }
        System.out.println("\nGLOBAL MATERIALS");
        for (int i = 0; i < global_materials.size(); i++) {
            System.out.println(global_materials.get(i).name());
        }
        System.out.println("\nGLOBAL MINES");
        for (int i = 0; i < global_mines.size(); i++) {
            System.out.println(global_mines.get(i).name());
        }
        System.out.println("\nGLOBAL TOOLS");
        for (int i = 0; i < global_tools.size(); i++) {
            System.out.println(global_tools.get(i).name());
        }
        */
    }

    @Override
    public void onDisable() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeGlobalMats() {

        JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("../spigot/plan.json")) {

            JSONObject obj = (JSONObject) jsonParser.parse(reader);

            // Read in string arrays from plan json file, match with material
            JSONArray tool_names_obj =  (JSONArray) obj.get("tools");
            for (Object tool_name_obj : tool_names_obj) {
                global_tools.add(Material.getMaterial(tool_name_obj.toString()));
            }
            JSONArray material_names_obj =  (JSONArray) obj.get("materials");
            for (Object material_name_obj : material_names_obj) {
                global_materials.add(Material.getMaterial(material_name_obj.toString()));
            }
            JSONArray mine_names_obj =  (JSONArray) obj.get("mines");
            for (Object mine_name_obj : mine_names_obj) {
                global_mines.add(Material.getMaterial(mine_name_obj.toString()));
            }

            // System.out.println(global_tools);
            // System.out.println(global_materials);
            // System.out.println(global_mines);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

//        // Tools
//        global_tools.add(Material.DIAMOND_PICKAXE);
//        global_tools.add(Material.DIAMOND_AXE);
//        global_tools.add(Material.DIAMOND_HOE);
//        global_tools.add(Material.DIAMOND_SHOVEL);
//        global_tools.add(Material.GOLDEN_PICKAXE);
//        global_tools.add(Material.GOLDEN_AXE);
//        global_tools.add(Material.GOLDEN_HOE);
//        global_tools.add(Material.GOLDEN_SHOVEL);
//        global_tools.add(Material.IRON_PICKAXE);
//        global_tools.add(Material.IRON_AXE);
//        global_tools.add(Material.IRON_HOE);
//        global_tools.add(Material.IRON_SHOVEL);
//
//        // Materials
//        global_materials.add(Material.WHITE_WOOL);
//        global_materials.add(Material.BLACK_WOOL);
//        global_materials.add(Material.BLUE_WOOL);
//        global_materials.add(Material.BROWN_WOOL);
//        global_materials.add(Material.CYAN_WOOL);
//        global_materials.add(Material.GRAY_WOOL);
//        global_materials.add(Material.GREEN_WOOL);
//        global_materials.add(Material.LIME_WOOL);
//        global_materials.add(Material.MAGENTA_WOOL);
//        global_materials.add(Material.RED_WOOL);
//        global_materials.add(Material.YELLOW_WOOL);
//        global_materials.add(Material.ORANGE_WOOL);
//
//        // global_materials.add(Material.GRAVEL);
//        // global_materials.add(Material.SAND);
//        // global_materials.add(Material.RED_SAND);
//
//        global_materials.add(Material.COBBLESTONE);
//        global_materials.add(Material.SOUL_SAND);
//
//        // Mines
//        //global_mines.add(Material.GOLD_BLOCK);
//        //global_mines.add(Material.DIAMOND_BLOCK);
//        //global_mines.add(Material.EMERALD_BLOCK);
//        //global_mines.add(Material.LAPIS_BLOCK);
//        //global_mines.add(Material.REDSTONE_BLOCK);
//        //global_mines.add(Material.IRON_BLOCK);
//        //global_mines.add(Material.OBSIDIAN);
//        global_mines.add(Material.ACACIA_PLANKS);
//        global_mines.add(Material.BIRCH_PLANKS);
//        global_mines.add(Material.DARK_OAK_PLANKS);
//        global_mines.add(Material.JUNGLE_PLANKS);
//        global_mines.add(Material.OAK_PLANKS);
//        global_mines.add(Material.SPRUCE_PLANKS);
//
//        // Shuffle
//        Collections.shuffle(global_materials);
//        Collections.shuffle(global_tools);
//        Collections.shuffle(global_mines);
    }
}


class PluginListener implements Listener {

    private final situatedDialogue plugin;

    public PluginListener(situatedDialogue plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        Material tool_used = event.getPlayer().getInventory().getItemInMainHand().getType();
        Material block_broken = event.getBlock().getType();
        Boolean success = false;
        // If mine
        for (int[] action : plugin.mine_actions) {
            if (plugin.mines.get(action[0]) == block_broken) {
                success = true;
                // Cancel breaking event
                event.setCancelled(true);
                // Spawn new material ontop of mine
                Location mine_location = event.getBlock().getLocation();
                mine_location.setY(mine_location.getBlockY() + 1);
                Block spawn_block = Objects.requireNonNull(mine_location.getWorld()).getBlockAt(mine_location);
                if (spawn_block.getType() == Material.AIR) { spawn_block.setType(plugin.materials.get(action[1])); }
                else { event.getPlayer().sendMessage("You can mine a new block only if the space above the mine is empty."); }
                break;
            }
        }
        // If "move"
        for (int[] action : plugin.move_actions) {
            if (plugin.materials.get(action[0]) == block_broken && plugin.tools.get(action[1]) == tool_used) {
                success = true;
                // Cancel breaking event for simplicity in implementation
                event.setCancelled(true);
                // Destroy material
                Location destroy_location = event.getBlock().getLocation();
                Material destroy_material = event.getBlock().getType();
                event.getBlock().setType(Material.AIR);
                // Place material in hand by...
                // Moving tool in slot 0 to another empty slot, only if there previously was an item at slot 0.
                if (event.getPlayer().getInventory().getItem(0) != null) {
                    for (int i = 1; i <= 35; i++) { // Inventory slots go from 0 to 36.
                        // Found empty slot?
                        if (event.getPlayer().getInventory().getItem(i) == null) {
                            // Set empty slot to contain item at inventory slot 0
                            event.getPlayer().getInventory().setItem(i,
                                    new ItemStack(event.getPlayer().getInventory().getItem(0).getType(), 1));
                            // Empty inventory slot 0
                            event.getPlayer().getInventory().setItem(0, null);
                            break;
                        }
                    }
                }
                // Give player destroyed item to position 0
                event.getPlayer().getInventory().setItem(0, new ItemStack(block_broken, 1));
                // Change current player selected slot to 0
                event.getPlayer().getInventory().setHeldItemSlot(0);
                break;
            }
        }
        // If neither
        if (!success) {
            event.getPlayer().sendMessage("You " + ChatColor.RED + "cannot" + ChatColor.WHITE + " break this block" +
                    " with the tool you are currently holding - " + ChatColor.RED + ". Refer to the Player Manual.");
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        Block block_placed = event.getBlock();
        Location block_placed_location = event.getBlock().getLocation();

        Location block_underneath_placed_location = event.getBlock().getLocation();
        block_underneath_placed_location.setY(block_underneath_placed_location.getY() - 1);
        Block block_underneath_placed = Objects.requireNonNull(block_placed_location.getWorld()).getBlockAt(block_underneath_placed_location);

        Material material_placed = block_placed.getType();
        Material material_underneath_placed = block_underneath_placed.getType();

        for (int[] action : plugin.combine_actions) {
            if (plugin.materials.get(action[0]) == material_underneath_placed &&
                    plugin.materials.get(action[1]) == material_placed) {
                // Destroy both
                block_placed.setType(Material.AIR);
                block_underneath_placed.setType(Material.AIR);
                // Spawn new
                block_underneath_placed.setType(plugin.materials.get(action[2]));
                break;
            }
        }
    }

    @EventHandler
    public void onHotbarSwitch(PlayerItemHeldEvent event) {
        // Invariants
        // (1) Player can only have, at most, 1 material in their inventory. They can however have many tools.
        // (2) If the player is currently holding a material, they cannot switch to another inventory slot.
        if (event.getPlayer().getInventory().getItem(event.getPreviousSlot()) != null) {
            if (plugin.materials.contains(event.getPlayer().getInventory().getItem(event.getPreviousSlot()).getType())) {
                event.getPlayer().sendMessage("You cannot switch to hold another item unless you place this item on the ground first.");
                event.setCancelled(true);
            }
        }
        // TODO: Add more robust error correction should the need arise (aggressively poll inventory).
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) throws SQLException {
        Player joined = event.getPlayer();
        System.out.println("\nPLAYER " + joined.getName() + " HAS JOINED.");
        // If it's not the first time that players are joining...
        if (joined.isOp()) { initializeOperator(joined); }
        else if (joined.getUniqueId().equals(this.plugin.player1_UUID)) {
            System.out.println("THEY ARE PLAYER ONE (1).");
            joined.setGameMode(GameMode.SURVIVAL); // For redundancy purposes
        } else if (joined.getUniqueId().equals(this.plugin.player2_UUID)) {
            System.out.println("THEY ARE PLAYER TWO (2).");
            joined.setGameMode(GameMode.SURVIVAL); // For redundancy purposes
        }
        // If it's their first time joining...
        else { initializePlayer(joined); };
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if ((!event.getPlayer().isOp()) && this.plugin.freeze) {
            Location from = event.getFrom();
            Location to = event.getTo();
            if (Math.floor(to.getX()) != Math.floor(from.getX()) || Math.floor(to.getZ()) != Math.floor(from.getZ())) {
                event.getPlayer().teleport(new Location(from.getWorld(), from.getX(), from.getY(), from.getZ()));
                event.getPlayer().sendMessage("Until all players have joined, you cannot move.");
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (this.plugin.noItemDropping) { event.setCancelled(true); }
    }

    private void initializeOperator(Player joined) throws SQLException {
        System.out.println("THEY ARE AN OPERATOR.");
        joined.setGameMode(GameMode.CREATIVE);
        clearWorld(joined.getWorld());
        spawnMines(joined.getWorld());
//        enableMovementLogging(); // TODO: This line here is for testing purposes only
    }

    private void initializePlayer(Player joined) throws SQLException {
        // Player 1 slot is empty?
        if (this.plugin.player1_UUID == null) {
            this.plugin.player1_UUID = joined.getUniqueId(); // Set UUID
            World blockWorld = Bukkit.getWorld("flat_world"); // Teleport player to their spawn
            Location player1_spawn = new Location(blockWorld, this.plugin.player1_spawn[0], this.plugin.player1_spawn[1], this.plugin.player1_spawn[2]);
            joined.teleport(player1_spawn);
            initializeInventory(joined, 1);
            System.out.println("THEY ARE PLAYER ONE (1).");
            joined.sendMessage("YOU ARE PLAYER ONE (1). PLEASE GO TO <same_ip>:8080/player1 IN A WEB BROWSER.");
        } else if (this.plugin.player2_UUID == null) {
            this.plugin.player2_UUID = joined.getUniqueId(); // Set UUID
            World blockWorld = Bukkit.getWorld("flat_world"); // Teleport player to their spawn
            Location player2_spawn = new Location(blockWorld, this.plugin.player2_spawn[0], this.plugin.player2_spawn[1], this.plugin.player2_spawn[2]);
            joined.teleport(player2_spawn);
            initializeInventory(joined, 2);
            System.out.println("THEY ARE PLAYER TWO (2).");
            joined.sendMessage("YOU ARE PLAYER TWO (2). PLEASE GO TO <same_ip>:8080/player2 IN A WEB BROWSER.");
            // When both players have joined, Unfreeze, initialize world, send server-wide message, start logging
            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            long unixTime = System.currentTimeMillis() / 1000L;
            String command = "replay start " + String.valueOf(unixTime);
            Bukkit.dispatchCommand(console, command);
            this.plugin.freeze = false;
            // Begin movement logging (async process)
            enableMovementLogging();
            Bukkit.getOnlinePlayers().forEach(p -> p.sendMessage("All players have joined! You may now move."));
            clearWorld(joined.getWorld());
            spawnMines(joined.getWorld());
        } else {
            System.out.println("SOMETHING IS VERY WRONG.");
            // TODO: Throw some exception here. PRIORITY: LOW
        }
    }

    // Begin movement logging (async process)
    private void enableMovementLogging() throws SQLException {

        // Create table in database if not exists.
        // SCHEMA: movementLogs
        //id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT
        // p_name VARCHAR(50)
        // p_uuid VARCHAR(50)
        // x,y,z,pitch,yaw DOUBLE
        // ts timestamp default current_timestamp
        String sqlCreate = "create table IF NOT EXISTS movementLogs(" +
                "id INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT," +
                "p_name VARCHAR(50)," +
                "p_uuid VARCHAR(50)," +
                "x DOUBLE," +
                "y DOUBLE," +
                "z DOUBLE," +
                "pitch DOUBLE," +
                "yaw DOUBLE," +
                "ts timestamp default current_timestamp)";
        Statement stmt = this.plugin.connection.createStatement();
        stmt.execute(sqlCreate);

        Bukkit.getScheduler().runTaskTimer(this.plugin, new Runnable() {
                    public void run() {
                        for (Player p: Bukkit.getOnlinePlayers()) {
                            Location p_location = p.getLocation();

                            String p_name = p.getName();
                            UUID p_uuid = p.getUniqueId();
                            double x = p_location.getX();
                            double y = p_location.getY();
                            double z = p_location.getZ();
                            float pitch = p_location.getPitch();
                            float yaw = p_location.getYaw();

//                            System.out.println(
//                                    p_name + " " + String.valueOf(p_uuid) + " "
//                                    + String.valueOf(x) + " "
//                                    + String.valueOf(y) + " "
//                                    + String.valueOf(z) + " "
//                                    + String.valueOf(pitch) + " "
//                                    + String.valueOf(yaw));

                            String insert = "insert into movementLogs(p_name, p_uuid, x, y, z, pitch, yaw)" + " values (?, ?, ?, ?, ?, ?, ?)";
                            try {
                                PreparedStatement stmt = plugin.connection.prepareStatement(insert);
                                stmt.setString(1, p_name);
                                stmt.setString(2, String.valueOf(p_uuid));
                                stmt.setDouble(3, x);
                                stmt.setDouble(4, y);
                                stmt.setDouble(5, z);
                                stmt.setDouble(6, pitch);
                                stmt.setDouble(7, yaw);
                                stmt.execute();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
        }, 0, this.plugin.logFrequency_ticks);
    }

    // Initialize player inventories with tools.
    private void initializeInventory(Player player, int player_n) {
        // 1. Clear inventory
        player.getInventory().clear();
        // 2. Set inventory
        if (player_n == 1) {
            for (int i = 0; i < plugin.player1_starting_inventory.size(); i++) {
                player.getInventory().setItem(i, new ItemStack(plugin.player1_starting_inventory.get(i), 1));
            }
        } else if (player_n == 2) {
            for (int i = 0; i < plugin.player2_starting_inventory.size(); i++) {
                player.getInventory().setItem(i, new ItemStack(plugin.player2_starting_inventory.get(i), 1));
            }
        }
    }

    private void clearWorld(World world) {
        for (int x = plugin.gameBoundaries[0][0]; x <= plugin.gameBoundaries[0][1]; x++) {
            for (int y = plugin.gameBoundaries[1][0]; y <= plugin.gameBoundaries[1][1]; y++) {
                for (int z = plugin.gameBoundaries[2][0]; z <= plugin.gameBoundaries[2][1]; z++) {
                    Location clear_location = new Location(world, x, y, z);
                    Block clear_block = world.getBlockAt(clear_location);
                    // Don't clear Stadium materials
                    int[] xyz = {x,y,z};
                    boolean inStadiumBlockLocations = false;
                    for (int i = 0; i < plugin.stadiumBlockLocations.length; i++) {
                        if (Arrays.equals(xyz, plugin.stadiumBlockLocations[i])) {
                            inStadiumBlockLocations = true;
                            break;
                        }
                    }
                    if (inStadiumBlockLocations) { continue; }
                    clear_block.setType(Material.AIR);
                }
            }
        }
    }

    private void spawnMines(World world) {
        int mineSpawnIndex = 0;
        for (Material mine_material : plugin.mines) {
            int mine_spawn_x = plugin.mineSpawnLocations[mineSpawnIndex][0];
            int mine_spawn_y = plugin.mineSpawnLocations[mineSpawnIndex][1];
            int mine_spawn_z = plugin.mineSpawnLocations[mineSpawnIndex][2];
            // System.out.println(mine_spawn_x);
            // System.out.println(mine_spawn_y);
            // System.out.println(mine_spawn_z);
            Location mine_spawn_location = new Location(world, mine_spawn_x, mine_spawn_y, mine_spawn_z);
            Block mine_spawn_block = world.getBlockAt(mine_spawn_location);
            mine_spawn_block.setType(mine_material);
            mineSpawnIndex++;
        }
    }
}
