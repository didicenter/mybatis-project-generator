package ${packageName}.configuration.mvc;

import com.google.common.base.CaseFormat;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.naming.NoNameCoder;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.Xpp3Driver;

import java.io.Writer;
import java.util.Objects;


public class XStreamUtils {

    public static XStream customerXStream() {
        XStream xStream = new XStream(new Xpp3Driver(new NoNameCoder()) {
            @Override
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {
                    @Override
                    @SuppressWarnings("rawtypes")
                    public void startNode(String name, Class clazz) {
                        String[] names = name.split("\\.");
                        name = names[names.length - 1];
                        name = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, name);
                        super.startNode(name, clazz);
                    }

                    @Override
                    public String encodeNode(String name) {
                        return name;
                    }

                    @Override
                    protected void writeText(QuickWriter writer, String text) {
                        if (Objects.nonNull(text) && (text.contains("<") || text.contains(">"))) {
                            writer.write("<![CDATA[");
                            writer.write(text);
                            writer.write("]]>");
                        } else {
                            writer.write(text);
                        }
                    }
                };
            }
        });

        return xStream;

    }

}
