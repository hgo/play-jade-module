package play.modules.jade;

import play.Logger;
import play.Play;
import play.PlayPlugin;

public class JadePlugin extends PlayPlugin {
    private static JadeSession session_;

    public static JadeSession session() {
        return session_;
    }

    @Override
    public void onConfigurationRead() {
        String root = Play.configuration.containsKey("jade.dir") ? Play.configuration.getProperty("jade.dir") : Play.applicationPath + "/app/views/jades/";
        session_ = new JadeSession(root);
        try {
            session_.loadFileSystemTemplates();
        } catch (Exception e) {
            Logger.error("Error initializing Jade module: " + e.getMessage());
        }
        Logger.info("Jade module initialized");
    }
}
