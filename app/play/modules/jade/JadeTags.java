package play.modules.jade;

import groovy.lang.Closure;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import play.templates.FastTags;
import play.templates.GroovyTemplate.ExecutableTemplate;

@FastTags.Namespace("jade")
public class JadeTags extends FastTags {

    
    public static void _print(Map<?, ?> args, Closure body, PrintWriter out, ExecutableTemplate template, int fromLine) throws IOException {
        String key = args.get("arg").toString();
        Object context = args.get("context");
        JadeSession session = JadePlugin.session();
        out.print(session.toHtml(key, context));
    }
}
