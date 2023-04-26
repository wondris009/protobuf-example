package cz.sg.protobufexample

import cz.sg.protobuf.ProtobufTraining.Course
import cz.sg.protobuf.ProtobufTraining.Student
import cz.sg.protobuf.ProtobufTraining.Student.PhoneNumber
import cz.sg.protobuf.ProtobufTraining.Student.PhoneType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter
import org.springframework.web.client.RestTemplate


@Configuration
class ProtobufConfig {

    @Bean
    fun protobufConverter() = ProtobufHttpMessageConverter()

    @Bean
    fun restTemplate(hmc: ProtobufHttpMessageConverter) = RestTemplate(listOf(hmc))

    @Bean
    fun createTestCourses(): CourseRepository {
        val courses = mutableMapOf<Int, Course>()
        val c1 = Course.newBuilder()
            .setId(1)
            .setCourseName("REST with Spring")
            .addAllStudent(createTestStudents())
            .build()

        val c2 = Course.newBuilder()
            .setId(2)
            .setCourseName("Learn Spring Security")
            .addAllStudent(emptyList())
            .build()

        courses[c1.id] = c1
        courses[c2.id] = c2

        return CourseRepository(courses)
    }

    private fun createTestStudents(): List<Student> {
        val phone1 = createPhone("123456", PhoneType.MOBILE)
        val student1 = createStudent(1, "John", "Doe", "john.doe@speedygonzales.com", listOf(phone1))

        val phone2 = createPhone("234567", PhoneType.LANDLINE)
        val student2 = createStudent(2, "Richard", "Roe", "richard.roe@speedygonzales.com", listOf(phone2))

        val phone31 = createPhone("345678", PhoneType.MOBILE)
        val phone32 = createPhone("456789", PhoneType.LANDLINE)
        val student3 = createStudent(3, "Jane", "Doe", "jane.doe@speedygonzales.com", listOf(phone31, phone32))

        return listOf(student1, student2, student3)
    }

    private fun createStudent(id: Int, firstName: String, lastName: String, email: String, phones: List<PhoneNumber>): Student {
        return Student.newBuilder().setId(id).setFirstName(firstName).setLastName(lastName).setEmail(email).addAllPhone(phones).build()
    }

    private fun createPhone(number: String, type: PhoneType): PhoneNumber {
        return PhoneNumber.newBuilder().setNumber(number).setType(type).build()
    }

}

class CourseRepository(val courses: Map<Int, Course>)