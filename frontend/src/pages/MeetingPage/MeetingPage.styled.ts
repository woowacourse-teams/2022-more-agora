import styled from '@emotion/styled';

export const Layout = styled.div`
  display: flex;
  flex-direction: column;
  flex: 1;
  overflow: hidden;
`;

export const MeetingDetailSection = styled.section`
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
  padding: 0.75rem;
`;

export const UserListSection = styled.section`
  display: flex;
  flex-direction: column;
  flex: 1;
  overflow: hidden;
  width: 100%;
`;

export const UserList = styled.ul`
  // reset margin
  margin: 0;

  width: 100%;
  padding: 0.75rem;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  overflow-x: hidden;
  overflow-y: scroll;
`;

export const Paragraph = styled.p``;

export const UserDataBox = styled.div`
  flex: 1;
  display: flex;
  justify-content: center;
`;

export const Form = styled.form`
  display: flex;
  overflow: hidden;
`;
