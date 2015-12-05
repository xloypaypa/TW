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

    public void addBuffFrom(Object object) {
        this.buffFromMessageNodes.add(new BuffFromMessageNode(object));
    }

    public void addBuffFromMessage(BuffFromMessage buffFromMessage) {
        this.buffFromMessageNodes.addAll(buffFromMessage.buffFromMessageNodes);
    }

    public void clear() {
        this.buffFromMessageNodes.clear();
    }

    public class BuffFromMessageNode {
        Object object;

        public BuffFromMessageNode(Object object) {
            this.object = object;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            BuffFromMessageNode node = (BuffFromMessageNode) o;

            return !(object != null ? !object.equals(node.object) : node.object != null);
        }

        public Object getObject() {
            return object;
        }
    }
}
