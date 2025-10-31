package co.edu.uniquindio.chainr.Controller;

import co.edu.uniquindio.chainr.model.Handler;
import co.edu.uniquindio.chainr.model.LanguageHandler;
import co.edu.uniquindio.chainr.model.LengthHandler;
import co.edu.uniquindio.chainr.model.SpamHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ChainViewController {

    @FXML private TextArea txtMessage;
    @FXML private Spinner<Integer> spnMaxLen;
    @FXML private CheckBox cbLength;
    @FXML private CheckBox cbLanguage;
    @FXML private CheckBox cbSpam;

    @FXML private HBox boxCards;
    @FXML private TextArea txtLog;

    @FXML private Button btnRun;
    @FXML private Button btnReset;
    @FXML private Button btnSpamDemo;

    private final List<VBox> cards = new ArrayList<>();

    @FXML
    private void initialize() {
        spnMaxLen.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 500, 50, 5));
        txtMessage.setText("Hola, este mensaje es corto y limpio.");

        btnRun.setOnAction(e -> runChain());
        btnReset.setOnAction(e -> resetCards());
        btnSpamDemo.setOnAction(e -> txtMessage.setText("Oferta $$$ imperdible, compra ahora SPAM!"));

        resetCards();
    }

    private void resetCards() {
        boxCards.getChildren().clear();
        cards.clear();

        if (cbLength.isSelected()) addCard("LengthHandler(" + spnMaxLen.getValue() + ")", "PENDING", "#334155", 1, false);
        if (cbLanguage.isSelected()) addCard("LanguageHandler", "PENDING", "#334155", 1, false);
        if (cbSpam.isSelected()) addCard("SpamHandler", "PENDING", "#334155", 1, false);
        if (cards.isEmpty()) addCard("(sin filtros)", "PASSED", "#22c55e", 2, false);
    }

    private void addCard(String title, String status, String borderColor, int borderWidth, boolean dashed) {
        VBox card = new VBox(6);
        card.setPrefWidth(180);
        card.setMinHeight(90);
        card.setStyle(baseStyle(borderColor, borderWidth, dashed));
        Text t = new Text(title); t.setStyle("-fx-fill:#e2e8f0; -fx-font-weight:bold;");
        Text s = new Text(status); s.setStyle("-fx-fill:#94a3b8;");
        card.getChildren().addAll(t, s);
        boxCards.getChildren().add(card);
        cards.add(card);
    }

    private void markCard(VBox card, String state) {
        Text status = (Text) card.getChildren().get(1);
        status.setText(state);
        switch (state) {
            case "PENDING" -> card.setStyle(baseStyle("#334155", 1, false));
            case "PASSED"  -> card.setStyle(baseStyle("#22c55e", 2, false));
            case "BLOCKED" -> card.setStyle(baseStyle("#ef4444", 2, false));
            case "SKIPPED" -> card.setStyle(baseStyle("#64748b", 2, true));
        }
    }

    private String baseStyle(String borderColor, int bw, boolean dashed) {
        String base = "-fx-background-color:#0f172a; -fx-background-radius:14; -fx-border-radius:14; " +
                "-fx-border-color:" + borderColor + "; -fx-border-width:" + bw + ";";
        if (dashed) base += " -fx-border-style: segments(10,5) line-cap round;";
        return base;
    }

    private void runChain() {
        txtLog.clear();
        if (cards.isEmpty()) resetCards();

        String message = txtMessage.getText();
        int maxLen = spnMaxLen.getValue();

        List<Handler> handlers = new ArrayList<>();
        List<VBox> activeCards = new ArrayList<>();

        int idx = 0;
        if (cbLength.isSelected()) { handlers.add(new LengthHandler(maxLen)); activeCards.add(cards.get(idx++)); }
        if (cbLanguage.isSelected()) { handlers.add(new LanguageHandler()); activeCards.add(cards.get(idx++)); }
        if (cbSpam.isSelected()) { handlers.add(new SpamHandler()); activeCards.add(cards.get(idx)); }

        if (handlers.isEmpty()) {
            appendLog("(sin filtros) — no se aplicaron validaciones, el mensaje pasa");
            return;
        }

        activeCards.forEach(c -> markCard(c, "PENDING"));

        boolean allPassed = true;
        for (int i = 0; i < handlers.size(); i++) {
            Handler h = handlers.get(i);
            VBox card = activeCards.get(i);

            boolean ok = h.handle(message);
            appendLog("[" + handlerName(h, maxLen) + "] → " + (ok ? "PASSED" : "BLOCKED"));

            if (ok) {
                markCard(card, "PASSED");
            } else {
                markCard(card, "BLOCKED");
                for (int j = i + 1; j < activeCards.size(); j++) markCard(activeCards.get(j), "SKIPPED");
                allPassed = false;
                break;
            }
        }

        appendLog("\nResultado final: " + (allPassed ? "✅ PASA" : "❌ BLOQUEADO"));
    }

    private String handlerName(Handler h, int maxLen) {
        String n = h.getClass().getSimpleName();
        if (h instanceof LengthHandler) n += "(" + maxLen + ")";
        return n;
    }

    private void appendLog(String line) {
        txtLog.appendText(LocalTime.now() + "  " + line + "\n");
    }
}
