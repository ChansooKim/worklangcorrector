DROP TABLE IF EXISTS correction_dictionary;
CREATE TABLE correction_dictionary (
   id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 교정 데이터 ID
   alias VARCHAR(255) NOT NULL,          -- 텍스트 교정별 별칭
   corrected_text TEXT NOT NULL,         -- 올바른 텍스트
   category VARCHAR(100) DEFAULT NULL,   -- 텍스트 카테고리
   priority INT DEFAULT 0,               -- 교정 우선순위
   description TEXT NULL,                -- 설명 또는 메타정보
   active BOOLEAN DEFAULT TRUE,          -- 활성화 여부
   created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 시간
   updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 수정 시간
--    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP -- 수정 시간
);

DROP TABLE IF EXISTS incorrect_text;
CREATE TABLE incorrect_text (
    id BIGINT AUTO_INCREMENT PRIMARY KEY, -- 잘못된 텍스트 ID
    wrong_text TEXT NOT NULL,             -- 잘못된 텍스트
    correction_id BIGINT NOT NULL,        -- 연관된 올바른 텍스트 ID
    category VARCHAR(100) DEFAULT NULL,   -- 텍스트 카테고리 (선택적)
    active BOOLEAN DEFAULT TRUE,          -- 활성화 여부
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- 생성 시간
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP  -- 수정 시간
--     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 수정 시간
);