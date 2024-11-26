# Ứng dụng quản lý thư viện (Manage Library)
# I. Cách tải và cài đặt ứng dụng về máy của mình
* B1: Tải project về
  1. Cách 1: Trên Github, chọn Code => Download ZIP. 
  2. Cách 2: Mở terminal tại thư mục muốn lưu project, sử dụng lệnh git init để khởi tạo Git, sau đó dùng lệnh git clone https://github.com/DugnCon/webjava để clone project về máy.
* B2: Chạy ứng dụng
  1. Khi đã download project về máy, hãy kiểm tra xem IDE của hai bên có giống nhau không (ở đây tôi dùng Eclipse).
  2. Tìm tới cái main.java và chạy ứng dụng.
# II. Giới thiệu về quản lý thư viện
# Mô tả
Ứng dụng được thiết kế để hỗ trợ các nhu cầu tìm kiếm sách cho bạn. Ứng dụng được viết bằng ngôn ngữ Java và sử dụng thêm thư viện JavaFX. Ứng dựng sử dụng mô hình MVC (Model, View, Controller). Ứng dụng có nhiều tính năng cho lần Admin và User giúp linh hoạt hơn và có nhiều chức năng. Ứng dụng sử dụng MySQL làm nơi chứa dữ liệu cho các tài liệu sách...
  1. Ứng dụng được thiết kế nằm mục đích giúp người dùng tìm kiếm sách và đăng kí sách.
  2. Ứng dụng được viết bằng ngôn ngữ Java và thư viện hỗ trợ thêm là JavaFX.
  3. Ứng dụng dựa trên mô hình MVC.
  4. Ứng dụng được dùng cho cả Admin và User.
  5. Có các chức năng thêm sách, xóa sách, sửa đổi sách....
  6. Ứng dụng sử dụng MySQL để làm kho chứa thông tin.

# Biểu đồ UML
![UML](https://github.com/user-attachments/assets/89fa0a2d-6230-4afc-bfeb-e929a29564f1)

## Cài đặt ##
1. Clone từ repo github : `git clone https://github.com/nguyenmanhdung23020520/BTL-OOP`
2. Mở trong IDE *(khuyên dùng* [IntelliJ](https://www.jetbrains.com/idea/))
3. Cài đặt thư viên thêm trong folder `lib` vào project modules và library
4. Sử dụng [mySQL](https://dev.mysql.com/downloads/installer/) vào import file ```managelibSQL.sql``` và chạy tạo database , có thể chỉnh sửa data của các bảng tạo ra
5. Chạy project

( Chú ý : hãy cài đặt JavaFX tham khảo [Tại đây](https://openjfx.io/))

## Demo sử dụng ##
[![Video demo](https://img.youtube.com/vi/so74FvZ7t8E/0.jpg)](https://www.youtube.com/watch?v=so74FvZ7t8E)

## Game Trivia ##
1.Game có cách chơi tương tự ai là triệu phú ,người chơi sẽ phải chọn 1 trong 4 đáp án đúng

2.Nếu trả lời đúng câu hỏi sẽ được cộng thêm 10đ ,trả lời sai bị - 5đ

3.Các câu hỏi trong Game liên quan đến sách,các nhân vật và phim ảnh

4.Trò chơi sẽ kết thúc khi kho câu hỏi hêt hoặc người chơi thoát






