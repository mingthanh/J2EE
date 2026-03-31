// ====================== API SERVICE ====================== 
const API_BASE_URL = 'http://localhost:8080/api';

const I18N_MESSAGES = {
  vi: {
    common_language_vi: 'VI',
    common_language_en: 'EN',
    common_logout: 'Đăng xuất',
    common_cancel: 'Hủy',
    common_save: 'Lưu',
    common_back: 'Quay lại',
    common_refresh: 'Làm mới',
    common_view_all: 'Xem tất cả',
    common_create: 'Tạo mới',
    common_edit: 'Chỉnh sửa',
    common_delete: 'Xóa',
    common_view: 'Xem',
    nav_dashboard: 'Dashboard',
    nav_projects: 'Dự án',
    nav_issues: 'Công việc',
    nav_profile: 'Hồ sơ',
    login_title: 'Đăng nhập - Jolliebee',
    login_hero_title: 'Jolliebee',
    login_hero_copy: 'Nền tảng được thiết kế cho quản lý dự án, giúp nhóm theo dõi công việc, kiểm soát tác vụ và nắm tiến độ trong một không gian làm việc trực quan.',
    login_metric_focus: 'Tập trung',
    login_metric_usability: 'Dễ sử dụng',
    login_metric_team: 'Cho nhóm',
    login_welcome: 'Chào mừng bạn quay lại',
    login_subtitle: 'Đăng nhập để tiếp tục quản lý dự án, issue và công việc trong ngày.',
    login_card_title: 'Đăng nhập',
    login_card_copy: 'Nhập tài khoản và mật khẩu để vào dashboard mới.',
    login_username: 'Tên đăng nhập',
    login_password: 'Mật khẩu',
    login_username_placeholder: 'Ví dụ: admin',
    login_password_placeholder: 'Nhập mật khẩu',
    login_submit: 'Đăng nhập',
    login_register_prompt: 'Chưa có tài khoản?',
    login_register_link: 'Đăng ký ngay',
    register_title: 'Đăng ký - Jolliebee',
    register_hero_title: 'Jolliebee',
    register_hero_copy: 'Bắt đầu với tài khoản mới để quản lý dự án, issue và tiến độ công việc trong cùng một không gian trực quan.',
    register_step_label_1: 'Đang xử lý',
    register_step_label_2: 'Xem tất cả',
    register_step_label_3: 'Hồ sơ',
    register_step_1: 'Tập trung',
    register_step_2: 'Dễ sử dụng',
    register_step_3: 'Cho nhóm',
    register_visual_kicker: 'Onboarding',
    register_visual_title: 'Jolliebee Workspace',
    register_visual_chip: 'Bảo mật',
    register_visual_ready: 'Sẵn sàng',
    register_header: 'Tạo tài khoản mới',
    register_subtitle: 'Điền thông tin bên dưới để vào hệ thống quản lý dự án với giao diện mới.',
    register_username: 'Tên đăng nhập',
    register_email: 'Email',
    register_display_name: 'Tên hiển thị',
    register_password: 'Mật khẩu',
    register_confirm_password: 'Xác nhận mật khẩu',
    register_username_placeholder: 'Nhập tên đăng nhập',
    register_email_placeholder: 'Nhập địa chỉ email',
    register_display_name_placeholder: 'Nhập tên hiển thị',
    register_password_placeholder: 'Tối thiểu 6 ký tự',
    register_confirm_password_placeholder: 'Nhập lại mật khẩu',
    register_password_hint: 'Mật khẩu nên có ít nhất 6 ký tự để an toàn hơn.',
    register_submit: 'Đăng ký',
    register_login_prompt: 'Đã có tài khoản?',
    register_login_link: 'Đăng nhập',
    dashboard_title: 'Dashboard - Jolliebee',
    dashboard_badge: 'Quản lý dự án thông minh',
    dashboard_hero_title: 'Quản lý dự án theo cách rõ ràng và dễ thao tác.',
    dashboard_hero_copy: 'Chào mừng bạn quay trở lại. Đây là nơi để theo dõi tổng quan công việc, dự án gần đây và các công việc đang xử lý.',
    dashboard_create_project: 'Tạo dự án',
    dashboard_view_issues: 'Xem công việc',
    dashboard_quick_nav: 'Điều hướng nhanh',
    dashboard_overview_nav: 'Tổng quan dashboard',
    dashboard_projects_nav: 'Quản lý dự án',
    dashboard_issues_nav: 'Theo dõi công việc',
    dashboard_profile_nav: 'Thông tin cá nhân',
    dashboard_total_projects: 'Tổng dự án',
    dashboard_total_issues: 'Tổng công việc',
    dashboard_in_progress: 'Đang xử lý',
    dashboard_completed: 'Hoàn thành',
    dashboard_recent_projects: 'Dự án gần đây',
    dashboard_recent_copy: 'Danh sách dự án được trình bày gọn gàng để người dùng xem nhanh và thao tác ngay.',
    dashboard_no_projects: 'Chưa có dự án',
    dashboard_no_projects_copy: 'Hãy tạo dự án đầu tiên để bắt đầu quản lý công việc.',
    modal_create_project: 'Tạo dự án mới',
    modal_project_name: 'Tên dự án',
    modal_project_description: 'Mô tả',
    projects_title: 'Dự án - Jolliebee',
    projects_badge: 'Quản lý dự án',
    projects_hero_title: 'Danh sách dự án được trình bày rõ ràng và dễ lọc.',
    projects_hero_copy: 'Người dùng có thể tìm nhanh, xem trạng thái và thao tác sửa xóa ngay trên cùng một màn hình.',
    projects_search_placeholder: 'Tìm kiếm theo tên hoặc mô tả dự án...',
    projects_table_name: 'Tên dự án',
    projects_table_description: 'Mô tả',
    projects_table_status: 'Trạng thái',
    projects_table_actions: 'Hành động',
    projects_empty: 'Không có dự án',
    projects_empty_copy: 'Hãy tạo dự án đầu tiên để bắt đầu.',
    project_active: 'Hoạt động',
    project_inactive: 'Tạm dừng',
    project_detail_title: 'Chi tiết dự án - Jolliebee',
    project_detail_back: 'Quay lại danh sách',
    project_detail_badge: 'Project board',
    project_detail_create_issue: 'Tạo issue',
    project_detail_refresh: 'Làm mới',
    project_detail_members_title: 'Thành viên dự án',
    project_detail_members_copy: 'Thêm thành viên và gán role theo từng project.',
    project_detail_member_placeholder: 'Username hoặc email',
    project_detail_add_member: 'Thêm thành viên',
    project_detail_board_title: 'Board công việc',
    project_detail_board_copy: 'Kéo thả card giữa các cột để cập nhật tiến độ.',
    project_detail_key: 'Project key',
    project_detail_lead: 'Project lead',
    project_detail_total_issues: 'Tổng issue',
    project_detail_total_members: 'Thành viên',
    project_detail_empty_members: 'Chưa có thành viên nào.',
    project_detail_unknown_user: 'Người dùng không xác định',
    project_detail_empty_description: 'Chưa có mô tả',
    project_detail_no_assignee: 'Chưa giao',
    project_detail_view_issue: 'Chi tiết',
    project_detail_empty_issues: 'Không có issue',
    project_detail_drag_todo: 'Cần làm',
    project_detail_drag_progress: 'Đang làm',
    project_detail_drag_review: 'Chờ duyệt',
    project_detail_drag_done: 'Hoàn thành',
    project_detail_load_error: 'Không thể tải dự án',
    project_detail_update_status_success: 'Đã cập nhật trạng thái issue',
    project_detail_update_status_error: 'Không thể cập nhật trạng thái',
    project_detail_member_required: 'Hãy nhập username hoặc email',
    project_detail_add_member_success: 'Đã thêm thành viên vào dự án',
    project_detail_add_member_error: 'Không thể thêm thành viên',
    project_detail_remove_member: 'Kick khỏi dự án',
    project_detail_leave_project: 'Rời dự án',
    project_detail_manage_members_only_creator: 'Chỉ người tạo dự án mới có thể quản lý thành viên.',
    project_detail_remove_member_success: 'Đã xóa thành viên khỏi dự án',
    project_detail_remove_member_error: 'Không thể xóa thành viên',
    project_detail_leave_project_success: 'Bạn đã rời khỏi dự án',
    project_detail_leave_project_error: 'Không thể rời khỏi dự án',
    project_detail_creator_badge: 'Người tạo dự án',
    project_detail_confirm_remove_member: 'Bạn có chắc muốn xóa thành viên này khỏi dự án?',
    project_detail_confirm_leave_project: 'Bạn có chắc muốn rời khỏi dự án này?',
    project_edit_title: 'Chỉnh sửa dự án - Jolliebee',
    project_edit_back: 'Quay lại danh sách',
    project_edit_badge: 'Cập nhật dự án',
    project_edit_header: 'Chỉnh sửa thông tin dự án',
    project_edit_copy: 'Form được sắp xếp gọn, để người dùng sửa tên, mô tả và trạng thái mà không bị rối mắt.',
    project_edit_name: 'Tên dự án',
    project_edit_description: 'Mô tả',
    project_edit_partners: 'Partner thực hiện',
    project_edit_partners_placeholder: 'Nhập username hoặc email, mỗi người một dòng',
    project_edit_partners_hint: 'Có thể thêm nhiều partner bằng cách xuống dòng hoặc dùng dấu phẩy.',
    project_edit_active: 'Kích hoạt dự án',
    project_edit_cancel: 'Hủy',
    project_edit_save: 'Lưu thay đổi',
    project_edit_load_error: 'Lỗi khi tải dự án',
    project_edit_success: 'Dự án được cập nhật thành công',
    project_edit_error: 'Lỗi khi cập nhật dự án',
    issues_title: 'Công việc - Jolliebee',
    issues_badge: 'Theo dõi công việc',
    issues_hero_title: 'Theo dõi công việc theo mức ưu tiên và trạng thái một cách dễ nhìn.',
    issues_hero_copy: 'Bộ lọc, tìm kiếm và bố cục thẻ được sắp xếp lại để người dùng thao tác nhanh hơn trên cả máy tính và điện thoại.',
    issues_create: 'Tạo công việc',
    issues_search_placeholder: 'Tìm kiếm công việc...',
    issues_filter_priority: 'Tất cả độ ưu tiên',
    issues_filter_status: 'Tất cả trạng thái',
    issues_empty: 'Không có công việc',
    issues_empty_copy: 'Hãy tạo công việc đầu tiên để bắt đầu theo dõi tiến độ.',
    issue_priority_high: 'Cao',
    issue_priority_medium: 'Trung bình',
    issue_priority_low: 'Thấp',
    issue_status_open: 'Mở',
    issue_status_progress: 'Đang làm',
    issue_status_review: 'Chờ duyệt',
    issue_status_done: 'Hoàn thành',
    issue_detail_title: 'Chi tiết issue - Jolliebee',
    issue_detail_back: 'Quay lại issues',
    issue_detail_badge: 'Chi tiết issue',
    issue_detail_open_project: 'Mở project',
    issue_detail_assign_title: 'Phân công công việc',
    issue_detail_assign_copy: 'Gán issue cho thành viên trong project.',
    issue_detail_assign_button: 'Cập nhật assignee',
    issue_detail_comment_title: 'Bình luận',
    issue_detail_comment_copy: 'Trao đổi nhanh ngay trên issue.',
    issue_detail_comment_placeholder: 'Nhập nội dung bình luận',
    issue_detail_comment_submit: 'Gửi comment',
    issue_detail_key: 'Issue key',
    issue_detail_status: 'Trạng thái',
    issue_detail_priority: 'Độ ưu tiên',
    issue_detail_assignee: 'Assignee',
    issue_detail_empty_description: 'Chưa có mô tả',
    issue_detail_no_assignee: 'Chưa giao',
    issue_detail_select_member: '-- Chọn thành viên --',
    issue_detail_assign_required: 'Hãy chọn thành viên',
    issue_detail_assign_success: 'Đã cập nhật người được giao',
    issue_detail_assign_error: 'Không thể phân công',
    issue_detail_load_error: 'Không thể tải issue',
    issue_detail_empty_comments: 'Chưa có comment nào.',
    issue_detail_comment_required: 'Nội dung comment không được để trống',
    issue_detail_comment_success: 'Đã gửi comment',
    issue_detail_comment_error: 'Không thể gửi comment',
    issue_detail_unknown_user: 'Người dùng',
    profile_title: 'Hồ sơ - Jolliebee',
    profile_personal: 'Thông tin cá nhân',
    profile_personal_copy: 'Các thông tin chính được sắp xếp thành từng cụm để người dùng dễ xem và dễ cập nhật sau này.',
    profile_security: 'Bảo mật tài khoản',
    profile_security_copy: 'Khu vực này được thiết kế gọn hơn, nhìn phát biết ngay trạng thái tài khoản và thao tác đổi mật khẩu.',
    profile_username: 'Tên đăng nhập',
    profile_email: 'Email',
    profile_display_name: 'Tên hiển thị',
    profile_role: 'Vai trò',
    profile_status: 'Trạng thái tài khoản',
    profile_active: 'Hoạt động',
    profile_change_password: 'Đổi mật khẩu'
  },
  en: {
    common_language_vi: 'VI',
    common_language_en: 'EN',
    common_logout: 'Log out',
    common_cancel: 'Cancel',
    common_save: 'Save',
    common_back: 'Back',
    common_refresh: 'Refresh',
    common_view_all: 'View all',
    common_create: 'Create',
    common_edit: 'Edit',
    common_delete: 'Delete',
    common_view: 'View',
    nav_dashboard: 'Dashboard',
    nav_projects: 'Projects',
    nav_issues: 'Issues',
    nav_profile: 'Profile',
    login_title: 'Login - Jolliebee',
    login_hero_title: 'Jolliebee',
    login_hero_copy: 'Built for project management, the platform helps teams track tasks, manage issues, and stay on top of progress in one clear workspace.',
    login_metric_focus: 'Focused',
    login_metric_usability: 'Easy to use',
    login_metric_team: 'Team-ready',
    login_welcome: 'Welcome back',
    login_subtitle: 'Sign in to continue managing projects, issues, and today’s work.',
    login_card_title: 'Sign in',
    login_card_copy: 'Enter your account and password to access the new dashboard.',
    login_username: 'Username',
    login_password: 'Password',
    login_username_placeholder: 'Example: admin',
    login_password_placeholder: 'Enter your password',
    login_submit: 'Sign in',
    login_register_prompt: 'Don’t have an account?',
    login_register_link: 'Create one',
    register_title: 'Register - Jolliebee',
    register_hero_title: 'Jolliebee',
    register_hero_copy: 'Start with a new account to manage projects, issues, and team progress in one clear workspace.',
    register_step_label_1: 'In progress',
    register_step_label_2: 'View all',
    register_step_label_3: 'Profile',
    register_step_1: 'Focused',
    register_step_2: 'Easy to use',
    register_step_3: 'Team-ready',
    register_visual_kicker: 'Onboarding',
    register_visual_title: 'Jolliebee Workspace',
    register_visual_chip: 'Secure',
    register_visual_ready: 'Ready',
    register_header: 'Create a new account',
    register_subtitle: 'Fill in the information below to enter the project management system with the new interface.',
    register_username: 'Username',
    register_email: 'Email',
    register_display_name: 'Display name',
    register_password: 'Password',
    register_confirm_password: 'Confirm password',
    register_username_placeholder: 'Enter your username',
    register_email_placeholder: 'Enter your email address',
    register_display_name_placeholder: 'Enter your display name',
    register_password_placeholder: 'At least 6 characters',
    register_confirm_password_placeholder: 'Re-enter your password',
    register_password_hint: 'Your password should be at least 6 characters long.',
    register_submit: 'Register',
    register_login_prompt: 'Already have an account?',
    register_login_link: 'Sign in',
    dashboard_title: 'Dashboard - Jolliebee',
    dashboard_badge: 'Smart project management',
    dashboard_hero_title: 'Manage projects in a way that is clear and easy to act on.',
    dashboard_hero_copy: 'Welcome back. This is where you quickly monitor work, recent projects, and issues in progress.',
    dashboard_create_project: 'Create project',
    dashboard_view_issues: 'View issues',
    dashboard_quick_nav: 'Quick navigation',
    dashboard_overview_nav: 'Dashboard overview',
    dashboard_projects_nav: 'Project management',
    dashboard_issues_nav: 'Issue tracking',
    dashboard_profile_nav: 'Personal information',
    dashboard_total_projects: 'Total projects',
    dashboard_total_issues: 'Total issues',
    dashboard_in_progress: 'In progress',
    dashboard_completed: 'Completed',
    dashboard_recent_projects: 'Recent projects',
    dashboard_recent_copy: 'Projects are presented in a cleaner format so users can scan and act quickly.',
    dashboard_no_projects: 'No projects yet',
    dashboard_no_projects_copy: 'Create your first project to get started.',
    modal_create_project: 'Create new project',
    modal_project_name: 'Project name',
    modal_project_description: 'Description',
    projects_title: 'Projects - Jolliebee',
    projects_badge: 'Project management',
    projects_hero_title: 'Project lists are cleaner and easier to filter.',
    projects_hero_copy: 'Users can search quickly, review status, and edit or delete from a single screen.',
    projects_search_placeholder: 'Search by project name or description...',
    projects_table_name: 'Project name',
    projects_table_description: 'Description',
    projects_table_status: 'Status',
    projects_table_actions: 'Actions',
    projects_empty: 'No projects',
    projects_empty_copy: 'Create your first project to begin.',
    project_active: 'Active',
    project_inactive: 'Paused',
    project_detail_title: 'Project Details - Jolliebee',
    project_detail_back: 'Back to list',
    project_detail_badge: 'Project board',
    project_detail_create_issue: 'Create issue',
    project_detail_refresh: 'Refresh',
    project_detail_members_title: 'Project members',
    project_detail_members_copy: 'Add members and assign roles for this project.',
    project_detail_member_placeholder: 'Username or email',
    project_detail_add_member: 'Add member',
    project_detail_board_title: 'Work board',
    project_detail_board_copy: 'Drag cards between columns to update progress.',
    project_detail_key: 'Project key',
    project_detail_lead: 'Project lead',
    project_detail_total_issues: 'Total issues',
    project_detail_total_members: 'Members',
    project_detail_empty_members: 'No members yet.',
    project_detail_unknown_user: 'Unknown user',
    project_detail_empty_description: 'No description yet',
    project_detail_no_assignee: 'Unassigned',
    project_detail_view_issue: 'Details',
    project_detail_empty_issues: 'No issues',
    project_detail_drag_todo: 'To do',
    project_detail_drag_progress: 'In progress',
    project_detail_drag_review: 'Review',
    project_detail_drag_done: 'Done',
    project_detail_load_error: 'Unable to load project',
    project_detail_update_status_success: 'Issue status updated',
    project_detail_update_status_error: 'Unable to update status',
    project_detail_member_required: 'Please enter a username or email',
    project_detail_add_member_success: 'Member added to project',
    project_detail_add_member_error: 'Unable to add member',
    project_detail_remove_member: 'Remove from project',
    project_detail_leave_project: 'Leave project',
    project_detail_manage_members_only_creator: 'Only the project creator can manage members.',
    project_detail_remove_member_success: 'Member removed from project',
    project_detail_remove_member_error: 'Unable to remove member',
    project_detail_leave_project_success: 'You left the project',
    project_detail_leave_project_error: 'Unable to leave the project',
    project_detail_creator_badge: 'Project creator',
    project_detail_confirm_remove_member: 'Are you sure you want to remove this member from the project?',
    project_detail_confirm_leave_project: 'Are you sure you want to leave this project?',
    project_edit_title: 'Edit Project - Jolliebee',
    project_edit_back: 'Back to list',
    project_edit_badge: 'Update project',
    project_edit_header: 'Edit project information',
    project_edit_copy: 'The form is organized clearly so users can edit name, description, and status without distraction.',
    project_edit_name: 'Project name',
    project_edit_description: 'Description',
    project_edit_partners: 'Project partners',
    project_edit_partners_placeholder: 'Enter username or email, one per line',
    project_edit_partners_hint: 'You can add multiple partners by using new lines or commas.',
    project_edit_active: 'Activate project',
    project_edit_cancel: 'Cancel',
    project_edit_save: 'Save changes',
    project_edit_load_error: 'Error loading project',
    project_edit_success: 'Project updated successfully',
    project_edit_error: 'Error updating project',
    issues_title: 'Issues - Jolliebee',
    issues_badge: 'Issue tracking',
    issues_hero_title: 'Track issues by priority and status in a clearer way.',
    issues_hero_copy: 'Filters, search, and cards are reorganized so users can work faster on both desktop and mobile.',
    issues_create: 'Create issue',
    issues_search_placeholder: 'Search issues...',
    issues_filter_priority: 'All priorities',
    issues_filter_status: 'All statuses',
    issues_empty: 'No issues',
    issues_empty_copy: 'Create your first issue to start tracking work.',
    issue_priority_high: 'High',
    issue_priority_medium: 'Medium',
    issue_priority_low: 'Low',
    issue_status_open: 'Open',
    issue_status_progress: 'In Progress',
    issue_status_review: 'Review',
    issue_status_done: 'Done',
    issue_detail_title: 'Issue Details - Jolliebee',
    issue_detail_back: 'Back to issues',
    issue_detail_badge: 'Issue details',
    issue_detail_open_project: 'Open project',
    issue_detail_assign_title: 'Assign work',
    issue_detail_assign_copy: 'Assign this issue to a project member.',
    issue_detail_assign_button: 'Update assignee',
    issue_detail_comment_title: 'Comments',
    issue_detail_comment_copy: 'Discuss directly on this issue.',
    issue_detail_comment_placeholder: 'Enter your comment',
    issue_detail_comment_submit: 'Send comment',
    issue_detail_key: 'Issue key',
    issue_detail_status: 'Status',
    issue_detail_priority: 'Priority',
    issue_detail_assignee: 'Assignee',
    issue_detail_empty_description: 'No description yet',
    issue_detail_no_assignee: 'Unassigned',
    issue_detail_select_member: '-- Select member --',
    issue_detail_assign_required: 'Please select a member',
    issue_detail_assign_success: 'Assignee updated',
    issue_detail_assign_error: 'Unable to assign issue',
    issue_detail_load_error: 'Unable to load issue',
    issue_detail_empty_comments: 'No comments yet.',
    issue_detail_comment_required: 'Comment content cannot be empty',
    issue_detail_comment_success: 'Comment sent',
    issue_detail_comment_error: 'Unable to send comment',
    issue_detail_unknown_user: 'User',
    profile_title: 'Profile - Jolliebee',
    profile_personal: 'Personal information',
    profile_personal_copy: 'Key information is grouped clearly so it is easier to scan and update later.',
    profile_security: 'Account security',
    profile_security_copy: 'This area is cleaner so users can instantly understand account status and change password actions.',
    profile_username: 'Username',
    profile_email: 'Email',
    profile_display_name: 'Display name',
    profile_role: 'Role',
    profile_status: 'Account status',
    profile_active: 'Active',
    profile_change_password: 'Change password'
  }
};

class I18nService {
  static defaultLanguage = 'vi';

  static getLanguage() {
    return localStorage.getItem('app-language') || this.defaultLanguage;
  }

  static setLanguage(language) {
    const nextLanguage = I18N_MESSAGES[language] ? language : this.defaultLanguage;
    localStorage.setItem('app-language', nextLanguage);
    this.applyLanguage(nextLanguage);
  }

  static t(key, fallback = '') {
    const language = this.getLanguage();
    return I18N_MESSAGES[language]?.[key] ?? I18N_MESSAGES[this.defaultLanguage]?.[key] ?? fallback ?? key;
  }

  static applyLanguage(language = this.getLanguage()) {
    document.documentElement.lang = language === 'en' ? 'en' : 'vi';

    document.querySelectorAll('[data-i18n]').forEach((element) => {
      const key = element.dataset.i18n;
      element.textContent = this.t(key, element.textContent);
    });

    document.querySelectorAll('[data-i18n-html]').forEach((element) => {
      const key = element.dataset.i18nHtml;
      element.innerHTML = this.t(key, element.innerHTML);
    });

    document.querySelectorAll('[data-i18n-placeholder]').forEach((element) => {
      const key = element.dataset.i18nPlaceholder;
      element.setAttribute('placeholder', this.t(key, element.getAttribute('placeholder') || ''));
    });

    const titleKey = document.body.dataset.titleKey;
    if (titleKey) {
      document.title = this.t(titleKey, document.title);
    }

    document.querySelectorAll('[data-lang-btn]').forEach((button) => {
      button.classList.toggle('active', button.dataset.langBtn === language);
    });

    document.dispatchEvent(new CustomEvent('app:languageChanged', { detail: { language } }));
  }
}

class ThemeService {
  static storageKey = 'app-theme';

  static getTheme() {
    const savedTheme = localStorage.getItem(this.storageKey);
    if (savedTheme === 'light' || savedTheme === 'dark') {
      return savedTheme;
    }

    return window.matchMedia('(prefers-color-scheme: dark)').matches ? 'dark' : 'light';
  }

  static setTheme(theme) {
    const nextTheme = theme === 'dark' ? 'dark' : 'light';
    localStorage.setItem(this.storageKey, nextTheme);
    document.documentElement.setAttribute('data-theme', nextTheme);
    this.updateToggle();
  }

  static toggleTheme() {
    this.setTheme(this.getTheme() === 'dark' ? 'light' : 'dark');
  }

  static mountToggle() {
    if (document.body?.dataset.disableThemeToggle === 'true') {
      return;
    }

    const host = document.querySelector('.navbar-actions')
      || document.querySelector('.auth-topbar-actions')
      || document.querySelector('.auth-topbar')
      || document.querySelector('.navbar-user');
    if (!host || host.querySelector('[data-theme-toggle]')) {
      return;
    }

    const button = document.createElement('button');
    button.type = 'button';
    button.className = 'theme-toggle';
    button.setAttribute('data-theme-toggle', 'true');
    button.addEventListener('click', () => this.toggleTheme());
    host.prepend(button);
    this.updateToggle();
  }

  static updateToggle() {
    const toggle = document.querySelector('[data-theme-toggle]');
    if (!toggle) {
      return;
    }

    const isDark = this.getTheme() === 'dark';
    const isEnglish = I18nService.getLanguage() === 'en';
    toggle.innerHTML = `
      <span class="theme-toggle-icon">${isDark ? '☀' : '☾'}</span>
      <span class="theme-toggle-text">${isDark
        ? (isEnglish ? 'Light' : 'Sáng')
        : (isEnglish ? 'Dark' : 'Tối')}</span>
    `;
    toggle.setAttribute(
      'aria-label',
      isDark
        ? (isEnglish ? 'Switch to light mode' : 'Chuyển sang giao diện sáng')
        : (isEnglish ? 'Switch to dark mode' : 'Chuyển sang giao diện tối')
    );
  }

  static init() {
    document.documentElement.setAttribute('data-theme', this.getTheme());
    this.mountToggle();
    this.updateToggle();
  }
}

class QuickSearchService {
  static state = {
    loading: false,
    items: [],
    loadedAt: 0,
  };

  static cacheTtlMs = 15000;

  static mount() {
    const navbarContainer = document.querySelector('.navbar-container');
    const navbarMenu = document.querySelector('.navbar-menu');
    if (!navbarContainer || !navbarMenu || document.querySelector('[data-quick-search]')) {
      return;
    }

    const wrapper = document.createElement('div');
    wrapper.className = 'quick-search';
    wrapper.setAttribute('data-quick-search', 'true');
    wrapper.innerHTML = `
      <div class="quick-search-box">
        <span class="quick-search-icon">⌕</span>
        <input
          type="text"
          id="quickSearchInput"
          class="quick-search-input"
          autocomplete="off"
          placeholder="${this.getPlaceholder()}"
          aria-label="${this.getPlaceholder()}"
        >
        <button type="button" class="quick-search-clear" id="quickSearchClear" aria-label="Clear search">×</button>
      </div>
      <div class="quick-search-results" id="quickSearchResults"></div>
    `;

    navbarMenu.insertAdjacentElement('afterend', wrapper);
    this.bindEvents();
  }

  static bindEvents() {
    const input = document.getElementById('quickSearchInput');
    const clearButton = document.getElementById('quickSearchClear');

    if (!input || !clearButton) {
      return;
    }

    input.addEventListener('focus', async () => {
      await this.ensureDataLoaded();
      this.renderResults(input.value);
    });

    input.addEventListener('input', async () => {
      await this.ensureDataLoaded();
      this.renderResults(input.value);
    });

    input.addEventListener('keydown', (event) => {
      if (event.key === 'Escape') {
        input.value = '';
        this.hideResults();
      }
    });

    clearButton.addEventListener('click', () => {
      input.value = '';
      input.focus();
      this.hideResults();
    });

    document.addEventListener('click', (event) => {
      const root = document.querySelector('[data-quick-search]');
      if (root && !root.contains(event.target)) {
        this.hideResults();
      }
    });
  }

  static normalizeText(value) {
    return (value || '')
      .toString()
      .normalize('NFD')
      .replace(/[\u0300-\u036f]/g, '')
      .toLowerCase()
      .trim();
  }

  static invalidate() {
    this.state.items = [];
    this.state.loadedAt = 0;
  }

  static async ensureDataLoaded(force = false) {
    if (this.state.loading) {
      return;
    }

    const isCacheFresh = this.state.items.length > 0
      && (Date.now() - this.state.loadedAt) < this.cacheTtlMs;

    if (!force && isCacheFresh) {
      return;
    }

    this.state.loading = true;
    try {
      const [projects, issues] = await Promise.all([
        ProjectService.getAll().catch(() => []),
        IssueService.getAll().catch(() => []),
      ]);

      this.state.items = [
        ...(projects || []).map((project) => ({
          id: project.id,
          type: 'project',
          title: project.name || 'Untitled project',
          subtitle: project.description || '',
          href: `/project/${project.id}`,
          keywords: [project.name, project.description, project.key].filter(Boolean).join(' '),
        })),
        ...(issues || []).map((issue) => ({
          id: issue.id,
          type: 'issue',
          title: issue.summary || issue.title || issue.issueKey || 'Untitled issue',
          subtitle: [issue.issueKey, issue.projectName, issue.status].filter(Boolean).join(' • '),
          href: `/issue/${issue.id}`,
          keywords: [
            issue.summary,
            issue.title,
            issue.issueKey,
            issue.description,
            issue.projectName,
            issue.status,
          ].filter(Boolean).join(' '),
        })),
      ];
      this.state.loadedAt = Date.now();
    } finally {
      this.state.loading = false;
    }
  }

  static renderResults(query) {
    const results = document.getElementById('quickSearchResults');
    const normalizedQuery = this.normalizeText(query);
    if (!results) {
      return;
    }

    if (!normalizedQuery) {
      results.innerHTML = `
        <div class="quick-search-empty">
          ${I18nService.getLanguage() === 'en'
            ? 'Type to search projects and issues'
            : 'Gõ để tìm nhanh dự án và issue'}
        </div>
      `;
      results.classList.add('active');
      return;
    }

    const matches = this.state.items
      .filter((item) => this.normalizeText(`${item.title} ${item.subtitle} ${item.keywords}`).includes(normalizedQuery))
      .slice(0, 8);

    if (matches.length === 0) {
      results.innerHTML = `
        <div class="quick-search-empty">
          ${I18nService.getLanguage() === 'en' ? 'No matching results' : 'Không tìm thấy kết quả phù hợp'}
        </div>
      `;
      results.classList.add('active');
      return;
    }

    results.innerHTML = matches.map((item) => `
      <a class="quick-search-item" href="${item.href}">
        <span class="quick-search-item-type ${item.type}">${item.type === 'project'
          ? (I18nService.getLanguage() === 'en' ? 'Project' : 'Dự án')
          : 'Issue'}</span>
        <div class="quick-search-item-body">
          <strong>${item.title}</strong>
          <span>${item.subtitle || (item.type === 'project'
            ? (I18nService.getLanguage() === 'en' ? 'Open project details' : 'Mở chi tiết dự án')
            : (I18nService.getLanguage() === 'en' ? 'Open issue details' : 'Mở chi tiết issue'))}</span>
        </div>
      </a>
    `).join('');
    results.classList.add('active');
  }

  static hideResults() {
    const results = document.getElementById('quickSearchResults');
    if (results) {
      results.classList.remove('active');
    }
  }

  static getPlaceholder() {
    return I18nService.getLanguage() === 'en'
      ? 'Quick search projects and issues...'
      : 'Tìm nhanh dự án và issue...';
  }

  static refreshTexts() {
    const input = document.getElementById('quickSearchInput');
    if (!input) {
      return;
    }

    input.placeholder = this.getPlaceholder();
    input.setAttribute('aria-label', this.getPlaceholder());

    if (document.activeElement === input) {
      this.renderResults(input.value);
    }
  }

  static init() {
    this.mount();
    this.refreshTexts();
  }
}

class ApiService {
  static async request(method, endpoint, data = null) {
    const token = localStorage.getItem('token');
    const options = {
      method,
      headers: {
        'Content-Type': 'application/json',
      }
    };

    if (token) {
      options.headers['Authorization'] = `Bearer ${token}`;
    }

    if (data) {
      options.body = JSON.stringify(data);
    }

    try {
      const response = await fetch(`${API_BASE_URL}${endpoint}`, options);
      
      if (!response.ok) {
        let errorMessage = `API Error: ${response.status}`;
        try {
          const errorBody = await response.json();
          errorMessage = errorBody.message || errorBody.error || errorMessage;
        } catch (parseError) {
          // Keep fallback message when response body is empty or not JSON.
        }
        if (response.status === 401) {
          localStorage.removeItem('token');
          window.location.href = '/login';
        }
        throw new Error(errorMessage);
      }

      if (response.status === 204) {
        return null;
      }

      return await response.json();
    } catch (error) {
      console.error('API Error:', error);
      throw error;
    }
  }

  static get(endpoint) {
    return this.request('GET', endpoint);
  }

  static post(endpoint, data) {
    return this.request('POST', endpoint, data);
  }

  static put(endpoint, data) {
    return this.request('PUT', endpoint, data);
  }

  static delete(endpoint) {
    return this.request('DELETE', endpoint);
  }
}

// ====================== AUTH SERVICE ====================== 
class AuthService {
  static async login(username, password) {
    try {
      const response = await fetch(`${API_BASE_URL}/login`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ username, password })
      });

      if (!response.ok) {
        throw new Error('Login failed');
      }

      const data = await response.json();
      localStorage.setItem('token', data.token);
      localStorage.setItem('user', JSON.stringify(data.user));
      return data;
    } catch (error) {
      console.error('Login Error:', error);
      throw error;
    }
  }

  static async register(username, email, password, displayName) {
    return ApiService.post('/users', {
      username,
      email,
      password,
      displayName,
      active: true
    });
  }

  static async logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('user');

    try {
      await fetch('/logout', {
        method: 'POST',
        credentials: 'same-origin'
      });
    } finally {
      window.location.replace('/login?logout');
    }
  }

  static getCurrentUser() {
    const user = localStorage.getItem('user');
    return user ? JSON.parse(user) : null;
  }

  static async ensureCurrentUser() {
    const existingUser = this.getCurrentUser();
    if (existingUser?.id) {
      return existingUser;
    }

    try {
      const user = await UserService.getCurrent();
      UIUtils.syncCurrentUser(user);
      return user;
    } catch (error) {
      return null;
    }
  }

  static isAuthenticated() {
    return document.cookie.includes('JSESSIONID=') || !!localStorage.getItem('token');
  }
}

// ====================== USER SERVICE ====================== 
class UserService {
  static getCurrent() {
    return ApiService.get('/users/me');
  }

  static getAll() {
    return ApiService.get('/users');
  }

  static getById(id) {
    return ApiService.get(`/users/${id}`);
  }

  static create(userData) {
    return ApiService.post('/users', userData);
  }

  static update(id, userData) {
    return ApiService.put(`/users/${id}`, userData);
  }

  static delete(id) {
    return ApiService.delete(`/users/${id}`);
  }
}

// ====================== PROJECT SERVICE ====================== 
class ProjectService {
  static getAll() {
    return ApiService.get('/projects');
  }

  static getById(id) {
    return ApiService.get(`/projects/${id}`);
  }

  static create(projectData) {
    return ApiService.post('/projects', projectData);
  }

  static update(id, projectData) {
    return ApiService.put(`/projects/${id}`, projectData);
  }

  static addMember(projectId, identifier, role = 'MEMBER') {
    return ApiService.post(`/projects/${projectId}/members`, { identifier, role });
  }

  static getMembers(projectId) {
    return ApiService.get(`/projects/${projectId}/members`);
  }

  static removeMember(projectId, userId) {
    return ApiService.delete(`/projects/${projectId}/members/${userId}`);
  }

  static leaveProject(projectId) {
    return ApiService.delete(`/projects/${projectId}/members/me`);
  }

  static delete(id) {
    return ApiService.delete(`/projects/${id}`);
  }
}

// ====================== ISSUE SERVICE ====================== 
class IssueService {
  static getAll() {
    return ApiService.get('/issues');
  }

  static getById(id) {
    return ApiService.get(`/issues/${id}`);
  }

  static create(issueData) {
    return ApiService.post('/issues', issueData);
  }

  static update(id, issueData) {
    return ApiService.put(`/issues/${id}`, issueData);
  }

  static getByProject(projectId) {
    return ApiService.get(`/issues/project/${projectId}`);
  }

  static assign(id, userId) {
    return ApiService.put(`/issues/${id}/assign/${userId}`);
  }

  static delete(id) {
    return ApiService.delete(`/issues/${id}`);
  }
}

// ====================== COMMENT SERVICE ====================== 
class CommentService {
  static getByIssue(issueId) {
    return ApiService.get(`/issues/${issueId}/comments`);
  }

  static create(issueId, commentData) {
    return ApiService.post(`/issues/${issueId}/comments`, commentData);
  }
}

class NotificationService {
  static emitter = null;
  static started = false;

  static start() {
    if (this.started || !AuthService.isAuthenticated()) {
      return;
    }

    this.started = true;
    this.emitter = new EventSource('/api/notifications/stream');
    this.emitter.addEventListener('issue-assigned', (event) => {
      try {
        const payload = JSON.parse(event.data);
        const message = I18nService.getLanguage() === 'en'
          ? `Assigned to ${payload.issueKey || 'an issue'}: ${payload.summary || ''}`
          : `Bạn được giao ${payload.issueKey || 'một công việc'}: ${payload.summary || ''}`;
        UIUtils.showAlert(message.trim(), 'info');
      } catch (error) {
        console.error('Notification parse error:', error);
      }
    });

    this.emitter.onerror = () => {
      if (this.emitter) {
        this.emitter.close();
      }
      this.started = false;
      window.setTimeout(() => this.start(), 5000);
    };
  }
}

// ====================== UI UTILITIES ====================== 
class UIUtils {
  static getDisplayName(user) {
    return user?.displayName || user?.username || 'U';
  }

  static renderAvatar(target, user) {
    if (!target) return;

    const name = this.getDisplayName(user);
    const initial = name.charAt(0).toUpperCase();
    const avatarUrl = user?.avatarUrl;
    const contentTarget = target.querySelector('.avatar-content') || target;

    if (avatarUrl) {
      contentTarget.innerHTML = `<img src="${avatarUrl}" alt="${name}" class="avatar-image">`;
    } else {
      contentTarget.textContent = initial;
    }
  }

  static syncCurrentUser(user) {
    if (!user) return;
    localStorage.setItem('user', JSON.stringify(user));
    document.dispatchEvent(new CustomEvent('app:userUpdated', { detail: { user } }));
  }

  static async fileToDataUrl(file) {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.onload = () => resolve(reader.result);
      reader.onerror = reject;
      reader.readAsDataURL(file);
    });
  }

  static showAlert(message, type = 'info') {
    const alertDiv = document.createElement('div');
    alertDiv.className = `alert alert-${type}`;
    alertDiv.textContent = message;
    
    const container = document.querySelector('.container') || document.body;
    container.insertBefore(alertDiv, container.firstChild);
    
    setTimeout(() => alertDiv.remove(), 5000);
  }

  static showSuccess(message) {
    this.showAlert(message, 'success');
  }

  static showError(message) {
    this.showAlert(message, 'danger');
  }

  static showModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
      modal.classList.add('active');
    }
  }

  static hideModal(modalId) {
    const modal = document.getElementById(modalId);
    if (modal) {
      modal.classList.remove('active');
    }
  }

  static validateEmail(email) {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  }

  static formatDate(date) {
    const locale = I18nService.getLanguage() === 'en' ? 'en-US' : 'vi-VN';
    return new Date(date).toLocaleDateString(locale);
  }

  static formatTime(date) {
    const locale = I18nService.getLanguage() === 'en' ? 'en-US' : 'vi-VN';
    return new Date(date).toLocaleTimeString(locale);
  }

  static showLoading() {
    const spinner = document.createElement('div');
    spinner.className = 'spinner';
    spinner.id = 'loading-spinner';
    document.body.appendChild(spinner);
  }

  static hideLoading() {
    const spinner = document.getElementById('loading-spinner');
    if (spinner) spinner.remove();
  }
}

// ====================== FORM VALIDATION ====================== 
class FormValidator {
  static validate(formData, rules) {
    const errors = {};

    for (const field in rules) {
      const value = formData[field];
      const fieldRules = rules[field];

      for (const rule of fieldRules) {
        const error = this.checkRule(value, rule);
        if (error) {
          errors[field] = error;
          break;
        }
      }
    }

    return errors;
  }

  static checkRule(value, rule) {
    if (rule.type === 'required' && !value) {
      return rule.message || 'Trường này là bắt buộc';
    }

    if (rule.type === 'email' && value && !this.isValidEmail(value)) {
      return rule.message || 'Email không hợp lệ';
    }

    if (rule.type === 'minLength' && value && value.length < rule.value) {
      return rule.message || `Tối thiểu ${rule.value} ký tự`;
    }

    if (rule.type === 'maxLength' && value && value.length > rule.value) {
      return rule.message || `Tối đa ${rule.value} ký tự`;
    }

    return null;
  }

  static isValidEmail(email) {
    return /^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email);
  }

  static displayErrors(formSelector, errors) {
    const form = document.querySelector(formSelector);
    if (!form) return;

    // Clear previous errors
    form.querySelectorAll('.error-message').forEach(el => el.remove());
    form.querySelectorAll('.form-control.is-invalid').forEach(el => el.classList.remove('is-invalid'));

    // Show new errors
    for (const field in errors) {
      const input = form.querySelector(`[name="${field}"]`);
      if (input) {
        input.classList.add('is-invalid');
        const errorDiv = document.createElement('div');
        errorDiv.className = 'error-message';
        errorDiv.textContent = errors[field];
        input.parentNode.appendChild(errorDiv);
      }
    }
  }
}

document.addEventListener('DOMContentLoaded', () => {
  ThemeService.init();
  I18nService.applyLanguage();
  QuickSearchService.init();
  NotificationService.start();
});

document.addEventListener('app:languageChanged', () => {
  ThemeService.updateToggle();
  QuickSearchService.refreshTexts();
});
