// source: https://blog.algomaster.io/p/solid-principles-explained-with-code
// Explanation with illustrations: https://medium.com/backticks-tildes/the-s-o-l-i-d-principles-in-pictures-b34ce2f1e898
public class SOLIDWithCode {

}

class SingleResponsibility {
    // Imagine you have a class called UserManager that handles user_authentication, user_profile_management, and email_notifications.
    class UserManager {
        public void authenticateUser(String username, String password) {
            // Logic for user authentication
        }

        public void updateUserProfile(String username, String newProfileData) {
            // Logic for updating user profile
        }

        public void sendEmailNotification(String email, String message) {
            // Logic for sending email notifications
        }
    }

    // This class violates the SRP because it has multiple responsibilities:
    // authentication, profile management, and email notifications.

    // If you need to change the way user authentication is handled, you might
    // inadvertently affect the email notification logic, or vice versa.

    // To adhere to the SRP, we can split this class into three separate classes,
    // each with a single responsibility:

    class UserAuthenticator {
        public void authenticateUser(String username, String password) {
            // Logic for user authentication
        }
    }

    class UserProfileManager {
        public void updateUserProfile(String username, String newProfileData) {
            // Logic for updating user profile
        }
    }

    class EmailNotifier {
        public void sendEmailNotification(String email, String message) {
            // Logic for sending email notifications
        }
    }
    // Now, each class has a single, well-defined responsibility. 
    // Changes to user authentication won't affect the email notification logic, and vice versa,
    // improving maintainability and reducing the risk of unintended side effects.
}

class OpenClosed {
    // Let's say you have a ShapeCalculator class that calculates the area and
    // perimeter of different shapes like rectangles and circles.
    class ShapeCalculator {
        public double calculate_area(String shapeType, double... dimensions) {
            if (shapeType.equals("rectangle")) {
                return dimensions[0] * dimensions[1]; // width * height
            } else if (shapeType.equals("circle")) {
                return Math.PI * dimensions[0] * dimensions[0]; // π * r^2
            }
            return 0;
        }

        public double calculate_perimeter(String shapeType, double... dimensions) {
            if (shapeType.equals("rectangle")) {
                return 2 * (dimensions[0] + dimensions[1]); // 2 * (width + height)
            } else if (shapeType.equals("circle")) {
                return 2 * Math.PI * dimensions[0]; // 2 * π * r
            }
            return 0;
        }
    }

    // If we want to add support for a new shape, like a triangle, we would have to
    // modify the calculate_area and calculate_perimeter methods, violating the
    // Open/Closed Principle.

    // To adhere to the OCP, we can create an abstract base class for shapes and
    // separate concrete classes for each shape type:
    abstract class Shape {
        public abstract double area();

        public abstract double perimeter();
    }

    class Rectangle extends Shape {
        private double width;
        private double height;

        public Rectangle(double width, double height) {
            this.width = width;
            this.height = height;
        }

        @Override
        public double area() {
            return width * height;
        }

        @Override
        public double perimeter() {
            return 2 * (width + height);
        }
    }

    class Circle extends Shape {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double area() {
            return Math.PI * radius * radius;
        }

        @Override
        public double perimeter() {
            return 2 * Math.PI * radius;
        }
    }

    // <code> similarly for triangle </code>

    // By introducing an abstraction (Shape class) and separating the concrete
    // implementations (Rectangle and Circle classes), we can add new shapes without
    // modifying the existing code.

    // The ShapeCalculator class can now work with any shape that implements the
    // Shape interface, allowing for easy extensibility.
}

class LiskovSubstitution {
    // Consider a base class Bird with a method fly().
    class Bird {
        public void fly() {
            System.out.println("Flying");
        }
    }

    // Now, let's say we have a subclass Penguin that extends Bird.
    class Penguin extends Bird {
        @Override
        public void fly() {
            throw new UnsupportedOperationException("Penguins cannot fly");
        }
    }

    // This violates the Liskov Substitution Principle because a Penguin cannot be
    // used in place of a Bird without causing unexpected behavior.

    // To adhere to the LSP, we can refactor the class hierarchy to separate flying
    // birds from non-flying birds:
    abstract class FlyingBird {
        public abstract void fly();
    }

    abstract class NonFlyingBird {
        // Non-flying birds do not have a fly method
    }

    class Sparrow extends FlyingBird {
        @Override
        public void fly() {
            System.out.println("Sparrow is flying");
        }
    }

    class PenguinCorrect extends NonFlyingBird {
        // Penguins do not have a fly method
    }

    // Now, Sparrow can be used wherever a FlyingBird is expected, and
    // PenguinCorrect can be used wherever a NonFlyingBird is expected, adhering to
    // the Liskov Substitution Principle.
}

// Another example - Vehicle containing method startEngine(), and having subclasses Car and Bicycle. 
// Bicycle cannot implement startEngine() as it doesn't have an engine.
// This violates LSP as Bicycle cannot be substituted for Vehicle without issues. 
// To fix this, we can change the method from startEngine() to start(),
// which both Car and Bicycle can implement appropriately.
// Another solution is to have separate base classes for MotorizedVehicle and
// NonMotorizedVehicle.

class InterfaceSegregation {
    // Let's consider a scenario where we have a media player application that
    // supports different types of media files, such as audio files (MP3, WAV) and
    // video files (MP4, AVI).

    // Without applying the ISP, we might have a single interface like this:

    interface MediaPlayer {
        void play_audio(String audioFile);

        void play_video(String videoFile);

        void stop_audio();

        void stop_video();

        void adjust_audio_volume(int level);

        void adjust_video_brightness(int level);
    }

    // In this case, any class that implements the MediaPlayer interface would be
    // forced to implement all the methods, even if it doesn't need them.

    // For example, an audio player would have to implement the play_video,
    // stop_video, and adjust_video_brightness methods, even though they are not
    // relevant for audio playback.

    // To adhere to the ISP, we can segregate the interface into smaller, more
    // focused interfaces:

    interface AudioPlayer {
        // FYI: Having multiple related methods does not violate SRP.
        // AutoPlayer - one responsibility: audio playback and its controls.
        void play_audio(String audioFile);

        void stop_audio();

        void adjust_audio_volume(int level);
    }

    interface VideoPlayer {
        void play_video(String videoFile);

        void stop_video();

        void adjust_video_brightness(int level);
    }

    // Now, we can have separate implementations for audio and video players:
    class MyAudioPlayer implements AudioPlayer {
        @Override
        public void play_audio(String audioFile) {
            // Logic to play audio file
        }

        @Override
        public void stop_audio() {
            // Logic to stop audio playback
        }

        @Override
        public void adjust_audio_volume(int level) {
            // Logic to adjust audio volume
        }
    }

    class MyVideoPlayer implements VideoPlayer {
        @Override
        public void play_video(String videoFile) {
            // Logic to play video file
        }

        @Override
        public void stop_video() {
            // Logic to stop video playback
        }

        @Override
        public void adjust_video_brightness(int level) {
            // Logic to adjust video brightness
        }
    }

    // By segregating the interfaces, each class only needs to implement the methods
    // it actually requires. This not only makes the code more maintainable but also
    // prevents clients from being forced to depend on methods they don't use.

    // If we need a class that supports both audio and video playback, we can create
    // a new class that implements both interfaces:
    class MyMediaPlayer implements AudioPlayer, VideoPlayer {
        @Override
        public void play_audio(String audioFile) {
            // Logic to play audio file
        }

        @Override
        public void stop_audio() {
            // Logic to stop audio playback
        }

        @Override
        public void adjust_audio_volume(int level) {
            // Logic to adjust audio volume
        }

        @Override
        public void play_video(String videoFile) {
            // Logic to play video file
        }

        @Override
        public void stop_video() {
            // Logic to stop video playback
        }

        @Override
        public void adjust_video_brightness(int level) {
            // Logic to adjust video brightness
        }
    }
}

// Another example of Interface Segregation Principle - consider an Restaurant
// Employee interface with methods cookFood(), serveFood(), and cleanRestaurant().
// A Chef class implementing this interface would be forced to implement
// serveFood() and cleanRestaurant() methods, which are not relevant to their role. 
// To adhere to ISP, we can create separate interfaces like Cook, Server,
// and Cleaner, allowing each class to implement only the methods relevant to
// their responsibilities.

class DependencyInversion {
    // Let's consider a example where we have a EmailService class that sends emails
    // using a specific email provider (e.g., Gmail).

    class GmailClient {
        public void sendEmail(String to, String subject, String body) {
            // Logic to send email using Gmail API
        }
    }

    class EmailService {
        private GmailClient gmailClient;

        public EmailService() {
            this.gmailClient = new GmailClient();
        }

        public void sendEmail(String to, String subject, String body) {
            gmailClient.sendEmail(to, subject, body);
        }
    }

    // In this example, the EmailService class directly depends on the GmailClient
    // class, a low-level concrete module that implements the details of sending emails using
    // the Gmail API.

    // This violates the DIP because the high-level EmailService module is tightly
    // coupled to the low-level GmailClient module.

    // To adhere to the DIP, we can introduce an abstraction (interface) for email clients:
    interface EmailClient {
        void sendEmail(String to, String subject, String body);
    }

    class GmailClientCorrect implements EmailClient {
        @Override
        public void sendEmail(String to, String subject, String body) {
            // Logic to send email using Gmail API
        }
    }

    class OutlookClient implements EmailClient {
        @Override
        public void sendEmail(String to, String subject, String body) {
            // Logic to send email using Outlook API
        }
    }

    class EmailServiceCorrect {
        private EmailClient emailClient;

        public EmailServiceCorrect(EmailClient emailClient) {
            this.emailClient = emailClient;
        }

        public void sendEmail(String to, String subject, String body) {
            emailClient.sendEmail(to, subject, body);
        }
    }

    class Client {
        public void execute() {
            EmailClient gmailClient = new GmailClientCorrect();
            EmailServiceCorrect emailService = new EmailServiceCorrect(gmailClient);
            emailService.sendEmail("example@example.com", "Subject", "Email body");
        }
    }
    // Now, the EmailService class depends on the EmailClient abstraction, and the
    // low-level email client implementations (GmailClient and OutlookClient) depend on the abstraction.
    // - Both high-level and low-level components depend on interfaces/abstractions.

    // This follows the DIP, resulting in a more flexible and extensible design.

    // Another example: OrderService directly creating a CreditCardPayment object,
    // it should depend on a PaymentProcessor interface, which can be implemented by
    // credit card or UPI payment classes. The required implementation is provided
    // through constructor dependency injection just like how it's done for
    // EmailClient in EmailService.

    // FYI: DIP vs dependency injection: DIP is a design principle; dependency
    // injection is one technique for implementing it.
}