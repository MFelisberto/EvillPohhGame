import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {
    private Clip clip;

    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);

            // Verifica se o arquivo de som existe
            if (!soundFile.exists()) {
                System.out.println("Arquivo de som não encontrado: " + soundFilePath);
                return;
            }

            // Carrega o arquivo de som como um fluxo de áudio
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);

            // Obtém um Clip para reproduzir o som
            clip = AudioSystem.getClip();

            // Abre o fluxo de áudio e carrega os dados no Clip
            clip.open(audioInputStream);

            // Reproduz o som uma vez
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}
