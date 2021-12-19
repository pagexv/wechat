package com.wechat;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.KeyEvent;

public class RobotUtil {

    private static Robot robot;
    private static Clipboard clip;
    private static Toolkit kit;


    /**
     * 置顶微信窗口
     *
     * @return
     */
    public static boolean topWeChat() {
        boolean flag = true;
        WinDef.HWND hwnd = User32.INSTANCE.FindWindow(null, "WeChat");
        if (hwnd == null) {
            flag = false;
            System.out.println("not running");
        } else {
            User32.INSTANCE.ShowWindow(hwnd, 9);
            User32.INSTANCE.SetForegroundWindow(hwnd); // bring to front
        }
        return flag;
    }

    /**
     * 初始化全局变量
     *
     */
    public static void init() {
        try {
            robot = new Robot();
        } catch (AWTException e) {
            robot = null;
            e.printStackTrace();
        }
        kit = Toolkit.getDefaultToolkit();
        clip = kit.getSystemClipboard();
    }

    /**
     * 查找需要发送消息的人员/群组
     *
     * @param userName
     */
    public static void queryItemForSendMessage(String itemName) {
        if (robot == null) {
            return;
        }
        robot.delay(2000); // 给窗口置顶预留时间
        // 模拟在微信上进行Ctrl+F进行查询操作
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_F);
        robot.keyRelease(KeyEvent.VK_CONTROL); // 是否Ctrl键
        // 将字符串放到剪切板内，相当于做了一次复制操作
        Transferable tText = new StringSelection(itemName);
        clip.setContents(tText, null);
        // 以下两行按下了ctrl+v，完成粘贴功能
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);// 释放ctrl按键，像ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放，不然会出问题。crtl如果按住没有释放，在按其他字母按键是，敲出来的回事ctrl的快捷键。
        robot.keyPress(KeyEvent.VK_ENTER); // 按下enter键进行查询
        robot.delay(2000); // 预留查询时间
        robot.keyPress(KeyEvent.VK_ENTER); // 再次按下enter键进行选中
        robot.delay(1000);
    }

    /**
     * 发送字符串消息
     *
     * @param message
     */
    public static void sendStrMessage(String message) {
        if (robot == null) {
            return;
        }
        // 将字符串放到剪切板内，相当于做了一次复制操作
        Transferable tText = new StringSelection(message);
        clip.setContents(tText, null);
        // 以下两行按下了ctrl+v，完成粘贴功能
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);// 释放ctrl按键，像ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放，不然会出问题。crtl如果按住没有释放，在按其他字母按键是，敲出来的回事ctrl的快捷键。
        robot.keyPress(KeyEvent.VK_ENTER); // 按下enter键进行消息发送
        robot.delay(1000);
    }

    /**
     * 发送字符串消息
     *
     * @param message
     */
    public static void sendImgMessage(String imgPath) {
        if (robot == null) {
            return;
        }
        // 将字符串放到剪切板内，相当于做了一次复制操作
        Transferable tImg = new ImageSelection(kit.getImage(imgPath));
        clip.setContents(tImg, null);
        // 以下两行按下了ctrl+v，完成粘贴功能
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        robot.keyRelease(KeyEvent.VK_CONTROL);// 释放ctrl按键，像ctrl，退格键，删除键这样的功能性按键，在按下后一定要释放，不然会出问题。crtl如果按住没有释放，在按其他字母按键是，敲出来的回事ctrl的快捷键。
        robot.keyPress(KeyEvent.VK_ENTER); // 按下enter键进行消息发送
        robot.delay(1000);
    }

}
/**
 * 自定义Transferable实现类实现图片复制到剪切板
 *
 * @date 2020/05/14
 */
class ImageSelection implements Transferable {
    private Image image;

    public ImageSelection(Image image) {
        this.image = image;
    }

    // Returns supported flavors
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[] {DataFlavor.imageFlavor};
    }



    // Returns true if flavor is supported
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return DataFlavor.imageFlavor.equals(flavor);
    }

    // Returns image
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
        if (!DataFlavor.imageFlavor.equals(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        return image;
    }
}
