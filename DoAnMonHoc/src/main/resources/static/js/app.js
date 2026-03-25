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
    nav_issues: 'Issues',
    nav_profile: 'Hồ sơ',
    login_title: 'Đăng nhập - Jolliebee',
    login_hero_title: 'Jolliebee',
    login_hero_copy: 'Nền tảng được thiết kế cho quản lý dự án, giúp nhóm theo dõi công việc, kiểm soát issue và nắm tiến độ trong một không gian làm việc trực quan.',
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
    register_hero_title: 'Bắt đầu quản lý dự án với quy trình gọn gàng và dễ theo dõi hơn.',
    register_hero_copy: 'Biểu mẫu đăng ký được tối giản để người dùng mới vào là hiểu ngay. Các trường thông tin rõ ràng, khoảng trắng thoáng và giao diện đồng bộ với toàn bộ hệ thống.',
    register_step_1: 'Tạo tài khoản',
    register_step_2: 'Tạo dự án',
    register_step_3: 'Theo dõi issue',
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
    dashboard_hero_copy: 'Chào mừng bạn quay trở lại. Đây là nơi để theo dõi tổng quan công việc, dự án gần đây và các issue đang xử lý.',
    dashboard_create_project: 'Tạo dự án',
    dashboard_view_issues: 'Xem issues',
    dashboard_quick_nav: 'Điều hướng nhanh',
    dashboard_overview_nav: 'Tổng quan dashboard',
    dashboard_projects_nav: 'Quản lý dự án',
    dashboard_issues_nav: 'Theo dõi issues',
    dashboard_profile_nav: 'Thông tin cá nhân',
    dashboard_total_projects: 'Tổng dự án',
    dashboard_total_issues: 'Tổng issues',
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
    issues_title: 'Issues - Jolliebee',
    issues_badge: 'Issue tracking',
    issues_hero_title: 'Theo dõi issue theo mức ưu tiên và trạng thái một cách dễ nhìn.',
    issues_hero_copy: 'Bộ lọc, tìm kiếm và card layout được sắp xếp lại để người dùng thao tác nhanh hơn trên cả desktop và mobile.',
    issues_create: 'Tạo issue',
    issues_search_placeholder: 'Tìm kiếm issue...',
    issues_filter_priority: 'Tất cả độ ưu tiên',
    issues_filter_status: 'Tất cả trạng thái',
    issues_empty: 'Không có issue',
    issues_empty_copy: 'Hãy tạo issue đầu tiên để theo dõi công việc.',
    issue_priority_high: 'Cao',
    issue_priority_medium: 'Trung bình',
    issue_priority_low: 'Thấp',
    issue_status_open: 'Mở',
    issue_status_progress: 'Đang làm',
    issue_status_done: 'Hoàn thành',
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
    register_hero_title: 'Start project management with a cleaner and easier workflow.',
    register_hero_copy: 'The signup form is simplified so new users understand it immediately. Fields are clear, spacing is comfortable, and the visual style matches the whole system.',
    register_step_1: 'Create account',
    register_step_2: 'Create project',
    register_step_3: 'Track issues',
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
    issue_status_done: 'Done',
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
        if (response.status === 401) {
          localStorage.removeItem('token');
          window.location.href = '/login';
        }
        throw new Error(`API Error: ${response.status}`);
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
      role: 'USER',
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

  static delete(id) {
    return ApiService.delete(`/issues/${id}`);
  }
}

// ====================== COMMENT SERVICE ====================== 
class CommentService {
  static getAll() {
    return ApiService.get('/comments');
  }

  static getById(id) {
    return ApiService.get(`/comments/${id}`);
  }

  static create(commentData) {
    return ApiService.post('/comments', commentData);
  }

  static update(id, commentData) {
    return ApiService.put(`/comments/${id}`, commentData);
  }

  static delete(id) {
    return ApiService.delete(`/comments/${id}`);
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
  I18nService.applyLanguage();
});
