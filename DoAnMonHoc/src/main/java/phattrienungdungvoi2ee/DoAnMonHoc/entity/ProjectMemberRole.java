package phattrienungdungvoi2ee.DoAnMonHoc.entity;

public enum ProjectMemberRole {
	PROJECT_ADMIN,
	PM,
	MEMBER,
	VIEWER,
	LEAD;

	public boolean isProjectAdmin() {
		return this == PROJECT_ADMIN || this == LEAD;
	}

	public boolean canManageProject() {
		return isProjectAdmin() || this == PM;
	}

	public boolean canEditIssues() {
		return this != VIEWER;
	}
}
