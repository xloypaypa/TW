package logic.buff.buffPackage;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by xlo on 15-12-5.
 * it's the buff from message
 */
public class BuffFromMessage {

    private final Set<BuffFromMessageNode> buffFromMessageNodes;

    public BuffFromMessage() {
        this.buffFromMessageNodes = new HashSet<>();
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BuffFromMessageNode that = (BuffFromMessageNode) o;

            return !(aClass != null ? !aClass.equals(that.aClass) : that.aClass != null)
                    && !(name != null ? !name.equals(that.name) : that.name != null);

        }

        public Class getaClass() {
            return aClass;
        }

        public String getName() {
            return name;
        }
    }
}
