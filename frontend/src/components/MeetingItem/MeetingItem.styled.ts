import styled from '@emotion/styled';
import { Link } from 'react-router-dom';
import { MeetingItemProps } from '.';

export const Layout = styled.div<{
  isActive: MeetingItemProps['meeting']['isActive'];
}>`
  display: inline-block;
  border-radius: 2rem;
  box-shadow: 0px 0px 1rem rgba(0, 0, 0, 0.1);

  color: ${({ isActive, theme: { colors } }) =>
    isActive ? colors['primary'] : colors['subtle-light']};
`;

export const MeetingItemLink = styled(Link)`
  color: inherit;
  text-decoration: inherit;
`;

export const Box = styled.div`
  box-sizing: border-box;
  width: 12.5rem;
  height: 12.5rem;
  padding: 1.75rem 1.25rem;

  display: flex;
  flex-direction: column;
  justify-content: space-between;
`;

export const MeetingBox = styled.div`
  display: flex;
  flex-direction: column;
  gap: 0.75rem;
`;

export const IconBox = styled.div`
  display: flex;
  gap: 0.5rem;
`;

export const IconSVG = styled.svg``;

export const MeetingNameSpan = styled.span`
  font-size: 1.25rem;
  font-weight: 400;
  color: ${({ theme: { colors } }) => colors['black']};
`;

export const MeetingTimeSpan = styled.span`
  font-size: 1.5rem;
  font-weight: 300;
  color: ${({ theme: { colors } }) => colors['subtle-dark']};
`;

export const MeetingStatusSpan = styled.span`
  align-self: flex-end;
  font-size: 1rem;
  font-weight: 700;
`;
