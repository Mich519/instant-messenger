package mj.project.gui.events;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import lombok.AllArgsConstructor;
import mj.project.configurations.AppConfig;
import mj.project.encryption.block_ciphers.BlockCipher;

@AllArgsConstructor
public class SelectBlockCypherEventHandler implements ChangeListener<Toggle> {

    private final ToggleGroup toggleGroup;

    @Override
    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

        if(toggleGroup.getSelectedToggle() != null) {
            BlockCipher blockCipher = (BlockCipher) toggleGroup.getSelectedToggle().getUserData();
            String blockCipherName = blockCipher.get();
            AppConfig.getInstance().setBlockCipher(blockCipherName);
        }
    }
}
