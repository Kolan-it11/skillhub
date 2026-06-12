CREATE TABLE enrollments (
    id BIGSERIAL PRIMARY KEY,
    enrolled_at TIMESTAMP,
    status VARCHAR(50),
    student_id BIGINT,
    course_id BIGINT,
    CONSTRAINT fk_enrollment_student FOREIGN KEY (student_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_enrollment_course FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE
);