package logic.buff.buffPackage;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xlo on 15-12-5.
 * it's the buff from message
 */
public class BuffFromMessage {

    private final List<BuffFromMessageNode> buffFromMessageNodes;

    public BuffFromMessage() {
        this.buffFromMessageNodes = new LinkedList<>();
    }

    public List<BuffFromMessageNode> getBuffFromMessageNodes() {
        return new LinkedList<>(this.buffFromMessageNodes);
    }

    public void addBuffFrom(Class aClass, String name) {
        this.buffFromMessageNodes.add(new BuffFromMessageNode(aClass, name));
    }

    public void addBuffFromMessage(BuffFromMessage buffFromMessage) {
        this.buffFromMessageNodes.addAll(buffFromMessage.buffFromMessageNodes);
    }

    public class BuffFromMessageNode {
        Class aClass;
        String name;

        public BuffFromMessageNode(Class aClass, String name) {
            this.aClass = aClass;
            this.name = name;
        }

        public Class getaClass() {
            return aClass;
        }

        public String getName() {
            return name;
        }
    }
}
