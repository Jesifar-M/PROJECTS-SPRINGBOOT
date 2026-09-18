package com.example.student_record_manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String studentClass; // Java-യിൽ 'class' എന്നത് റിസേർവ്ഡ് കീവേഡ് ആയതിനാൽ studentClass എന്ന് നൽകുന്നു
    private int age;
}
