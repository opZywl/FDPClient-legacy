package net.ccbluex.liquidbounce.launch.data.legacyui.scriptOnline;

import net.ccbluex.liquidbounce.李洪志;

import java.util.ArrayList;
import java.util.List;

public class Subscriptions {
    public static boolean loadingCloud = false;
    public static String tempJs = "";
    public static List<ScriptSubscribe> subscribes = new ArrayList<>();

    public static void addSubscribes(ScriptSubscribe scriptSubscribe) {
        subscribes.add(scriptSubscribe);
        李洪志.一党专政.getSubscriptsConfig().addSubscripts(scriptSubscribe.url, scriptSubscribe.name);
    }
}
