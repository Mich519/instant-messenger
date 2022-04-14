package mj.project.gui.events;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import mj.project.encryption.block_ciphers.BlockCipher;
import mj.project.encryption.data.KeyStorage;
import mj.project.networking.data.NetworkPropertiesStorage;

import javax.inject.Inject;

public class SelectBlockCypherEventHandler implements ChangeListener<Toggle> {

    private final ToggleGroup toggleGroup;
    private final KeyStorage keyStorage;

    public SelectBlockCypherEventHandler(ToggleGroup toggleGroup, KeyStorage keyStorage) {
        this.toggleGroup = toggleGroup;
        this.keyStorage = keyStorage;
    }

    @Override
    public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {

        if(toggleGroup.getSelectedToggle() != null) {
            BlockCipher blockCipher = (BlockCipher) toggleGroup.getSelectedToggle().getUserData();
            keyStorage.setBlockCipher(blockCipher);
        }
    }
}
