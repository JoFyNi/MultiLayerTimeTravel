import org.w3c.dom.css.Counter;

public class Laufzeit {
    Counter timeCounter = new Counter() {
        @Override
        public String getIdentifier() {
            return null;
        }

        @Override
        public String getListStyle() {
            return null;
        }

        @Override
        public String getSeparator() {
            return null;
        }
    };
}
