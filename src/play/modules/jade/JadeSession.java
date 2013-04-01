package play.modules.jade;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import de.neuland.jade4j.Jade4J;
import de.neuland.jade4j.template.JadeTemplate;

import play.vfs.VirtualFile;

public class JadeSession {

    private String root_ = null;
    private Map<String, JadeTemplate> loaded_ = new HashMap<String, JadeTemplate>();

    public JadeSession(String root) {
        this.root_ = root;
    }

    public void loadFileSystemTemplates() throws IOException {
        File dir = VirtualFile.open(this.root_).getRealFile();
        this.addFilesRecursively(dir, this.root_);
    }

    private void addFilesRecursively(File file, String root) throws IOException {
        File[] children = file.listFiles();
        if (children != null) {
            for (File child : children) {
                if (child.isDirectory()) {
                    addFilesRecursively(child, root);
                } else {
                    String path = child.getAbsolutePath();
                    String key = path.replace(root + "/", "");
                    this.addFromFile(key, path);
                }
            }
        }
    }

    private void addFromFile(String key, String path) throws IOException {
        loaded_.put(key, Jade4J.getTemplate(path));
    }

    public String toHtml(String key, Object context) throws IOException {
        if (!loaded_.containsKey(key)) {
            addFromFile(key, root_ + key);
        }
        JadeTemplate jade = loaded_.get(key);
        return Jade4J.render(jade, (Map<String, Object>) context);
    }

    // private void addFromString(String key, String tmpl) {
    // Jade4J.
    // loaded_.put(key, compiler().parse(tmpl));
    // raw_.put(key, tmpl);
    // }

}
