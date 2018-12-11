package me.tremor.Airglow_user.vault;

import android.content.Context;
import android.util.Log;

import com.bottlerocketstudios.vault.SharedPreferenceVault;
import com.bottlerocketstudios.vault.SharedPreferenceVaultFactory;
import com.bottlerocketstudios.vault.SharedPreferenceVaultRegistry;
import com.bottlerocketstudios.vault.keys.generator.Aes256KeyFromPasswordFactory;
import com.bottlerocketstudios.vault.salt.PrngSaltGenerator;
import com.bottlerocketstudios.vault.salt.SaltBox;
import com.bottlerocketstudios.vault.salt.SaltGenerator;
import com.bottlerocketstudios.vault.salt.SpecificSaltGenerator;

import java.security.GeneralSecurityException;

/**
 * Example initialization and place to keep reference to your vaults. This example instantiates all three
 * supported types, while most applications will only need one.
 */
public class VaultLocator {
    private static final String TAG = VaultLocator.class.getSimpleName();


    private static final String AUTOMATICALLY_KEYED_PREF_FILE_NAME = "automaticallyKeyedPref";
    private static final String AUTOMATICALLY_KEYED_KEY_FILE_NAME = "automaticallyKeyedKey";
    private static final String AUTOMATICALLY_KEYED_KEY_ALIAS = "automaticallyKeyed";
    private static final int AUTOMATICALLY_KEYED_KEY_INDEX = 3;
    private static final String AUTOMATICALLY_KEYED_PRESHARED_SECRET = "This is also obviously not what you want to use, come up with your own way of obscuring this value. A standard method demonstrated here will not be very useful if everyone does it.";


    public static boolean initializeVaults(Context context) {
        try {
            initAutomaticallyKeyedVault(context);
            return true;
        } catch (GeneralSecurityException e) {
            Log.e(TAG, "Failed to initialize vaults", e);
        }
        return false;
    }


    /**
     * Create a vault that will automatically key itself initially with a random key.
     */
    private static void initAutomaticallyKeyedVault(Context context) throws GeneralSecurityException {
        SharedPreferenceVault sharedPreferenceVault = SharedPreferenceVaultFactory.getAppKeyedCompatAes256Vault(context, AUTOMATICALLY_KEYED_PREF_FILE_NAME, AUTOMATICALLY_KEYED_KEY_FILE_NAME, AUTOMATICALLY_KEYED_KEY_ALIAS, AUTOMATICALLY_KEYED_KEY_INDEX, AUTOMATICALLY_KEYED_PRESHARED_SECRET);
        SharedPreferenceVaultRegistry.getInstance().addVault(AUTOMATICALLY_KEYED_KEY_INDEX, AUTOMATICALLY_KEYED_PREF_FILE_NAME, AUTOMATICALLY_KEYED_KEY_ALIAS, sharedPreferenceVault);
    }

    /**
     * Encapsulates index knowledge.
     */
    public static SharedPreferenceVault getAutomaticallyKeyedVault() {
        return SharedPreferenceVaultRegistry.getInstance().getVault(AUTOMATICALLY_KEYED_KEY_INDEX);
    }

}
