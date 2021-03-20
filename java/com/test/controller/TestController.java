package com.test.controller; // 0

import com.test.dto.LectureDto;
import com.test.dto.TestDto;
import com.test.service.test.LectureService;
import com.test.service.test.TestService;
import com.test.util.firebase.FirebaseMessagingSnippets;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.javassist.bytecode.FieldInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // 1
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.ObjectInputFilter;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

@Controller // 1
public class TestController { // 1

    @Autowired
    ServletContext servletContext;

    @Autowired
    TestService testService;
    @Autowired
    LectureService lectureService;
    @Autowired
    FirebaseMessagingSnippets firebaseMessagingSnippets;

    @GetMapping("/")
    public String main(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/admin";
    }

    @GetMapping("/admin/home")
    public String adminHome(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/home";
    }

    @GetMapping("/admin/data-table.do")
    public String dataTable(Model model){
        try{
            ArrayList<LectureDto> lectureList = lectureService.getItemList();
            model.addAttribute("lectureList", lectureList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/data-table";
    }

    @GetMapping("/admin/form.do")
    public String form(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/form";
    }

    @GetMapping("/admin/login.do")
    public String adminLogin(Model model){
        try{

        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/login";
    }

    @PostMapping(value = "/admin/addLecture.do")
    public String addLecture(@Param("lecCategory") String lecCategory,
                             @Param("lecName") String lecName,
                             @Param("lecPrice") int lecPrice,
                             @Param("lecImg") MultipartFile lecImg){
        try{
            // 한칸이라도 비어있다면 다 입력하라는 알림창이 뜨게 만들기
            // 동일한 사진이름이 있다면 ?
            System.out.println("hi from post");

            String filename = lecImg.getOriginalFilename();
            String lecImgPath = "/lecImgs/" + filename;

            if (!lecImg.isEmpty()){
                String dirPath = "C:\\Users\\Woody\\Desktop\\Coding\\wapp\\src\\main\\webapp\\resources\\lecImgs\\";
                lecImg.transferTo(new File(dirPath + filename));
            } else {
                lecImgPath = null;
            }




            lectureService.addItem(lecCategory, lecName, lecPrice, lecImgPath);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/data-table.do";
    }

    @GetMapping(value = "/admin/delete.do")
    public String delete(@RequestParam(value = "lecNo") int lecNo){
        try{
            LectureDto dblecture = lectureService.selectItem(lecNo);
            lectureService.deleteItem(lecNo);
            File prevfile = new File("C:\\Users\\Woody\\Desktop\\Coding\\wapp\\src\\main\\webapp\\resources" + dblecture.getLecImg());
            String prevname = prevfile.getName();
            if (prevfile.delete()) {
                System.out.println("Deleted the file: " + prevname);
            } else {
                System.out.println("Failed to delete the file.");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/data-table.do";
    }

    @GetMapping("/admin/edit.do")
    public String editForm(@RequestParam(value = "lecNo") int lecNo, Model model){
        try{
            System.out.println("lecNo: " + lecNo);
            LectureDto lecture = lectureService.selectItem(lecNo);
            model.addAttribute("lecture", lecture);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "admin/edit";
    }

    @RequestMapping(value = "/admin/editLecture.do", method = {RequestMethod.POST, RequestMethod.GET})
    public String editLecture(@Param("lecNo") int lecNo,
                              @Param("lecCategory") String lecCategory,
                             @Param("lecName") String lecName,
                             @Param("lecPrice") int lecPrice,
                              @Param("lecImg") MultipartFile lecImg){
        try{
            System.out.println("hi from edit");
            LectureDto dblecture = lectureService.selectItem(lecNo);
            try {// 이미지가 업로드되었다면 삭제, 새로 저장후 링크 db에 저장, 같은 이름의 이미지가 서버에 저장되어있는지 확인 필요!
                System.out.println("editItemWithImg");
                File prevfile = new File("C:\\Users\\Woody\\Desktop\\Coding\\wapp\\src\\main\\webapp\\resources" + dblecture.getLecImg());
                String prevname = prevfile.getName();
                if (prevfile.delete()) {
                    System.out.println("Deleted the file: " + prevname);
                } else {
                    System.out.println("Failed to delete the file.");
                }

                String filename = lecImg.getOriginalFilename();
                String lecImgPath = "/lecImgs/" + filename;
                System.out.println(lecImgPath);

                if (!lecImg.isEmpty()) {
                    String dirPath = "C:\\Users\\Woody\\Desktop\\Coding\\wapp\\src\\main\\webapp\\resources\\lecImgs\\";
                    lecImg.transferTo(new File(dirPath + filename));
                } else {
                    lecImgPath = null;
                }
                lectureService.editItem(lecNo, lecCategory, lecName, lecPrice, lecImgPath);
            } catch (NullPointerException e) {// 이미지를 새로 업로드하지 않았으면 데이터베이스에있는 이미지를 그대로 저장
                System.out.println("editItemWithoutImg");
                lectureService.editItem(lecNo, lecCategory, lecName, lecPrice, dblecture.getLecImg());
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/admin/data-table.do";
    }

















    @GetMapping("/test.do")
    public String test(Model model){
        try{
            System.out.println("test.do Controller");
            ArrayList<TestDto> itemList = testService.getItemList();
            model.addAttribute("itemList", itemList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "test";
    }

    @RequestMapping(value = "/get.do", method = RequestMethod.GET)
    public String get(@RequestParam(value = "data") String data){
        try{
            System.out.println("controller: " + data);
            testService.addItem(data);
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/test.do";
    }

    @RequestMapping(value = "/post.do", method = RequestMethod.POST)
    public String post(@RequestParam(value = "data") String data){
        try{
            System.out.println("post방식 data: " + data);
            testService.addItem(data);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/test.do";
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "number") String number){
        try{
            System.out.println("number: " + number);
            testService.deleteItem(number);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/test.do";
    }

    @GetMapping("/fcm.do")
    public String fcm(@RequestParam(value = "fcm") String fcm, @RequestParam(value = "title") String title, @RequestParam(value = "content") String content, HttpServletRequest req){
        try{
            System.out.println("fcm: " + fcm);
            System.out.println("title: " + title);
            System.out.println("content: " + content);
            firebaseMessagingSnippets.test_send_FCM(fcm, title, content, req);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
