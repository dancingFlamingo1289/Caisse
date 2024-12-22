package venteÉtalage;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class BarcodeGeneratorFrame extends Frame {
    private TextField dataTextField;
    private Button generateButton;
    private Label resultLabel;

    public BarcodeGeneratorFrame() {
        setTitle("Barcode Generator");
        setSize(400, 200);
        setLayout(new FlowLayout());

        dataTextField = new TextField("Hello, ZXing!", 20);
        generateButton = new Button("Generate QR Code");
        resultLabel = new Label("");

        add(dataTextField);
        add(generateButton);
        add(resultLabel);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String barcodeData = dataTextField.getText();
                generateQRCode(barcodeData);
            }
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
    }

    public void generateQRCode(String barcodeData) {
        try {
            Map<EncodeHintType, Object> hints = new HashMap<>();
            hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            BitMatrix bitMatrix = new MultiFormatWriter().encode(barcodeData, BarcodeFormat.QR_CODE, 200, 200, hints);

            BufferedImage barcodeImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
            File outputFile = new File("barcode.png");
            ImageIO.write(barcodeImage, "PNG", outputFile);

            resultLabel.setText("QR Code generated successfully!");
        } catch (Exception e) {
            resultLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        BarcodeGeneratorFrame frame = new BarcodeGeneratorFrame();
        frame.setVisible(true);
    }
}
