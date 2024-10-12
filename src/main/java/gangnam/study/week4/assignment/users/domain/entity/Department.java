package gangnam.study.week4.assignment.users.domain.entity;

public enum Department {

    DEVELOPMENT("개발 부서", 1L),
    HR("인사과 부서", 2L),
    DESIGN("디자인 부서", 3L);

    private final String description;
    private final Long id;

    // Enum 생성자
    Department(String description, Long id) {
        this.description = description;
        this.id = id;
    }

    // 부서 설명 반환
    public String getDescription() {
        return description;
    }

    // 부서 ID 반환
    public Long getId() {
        return id;
    }
}
