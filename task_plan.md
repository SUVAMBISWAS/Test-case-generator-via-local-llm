# Task Plan

## Phases

### Phase 1: Foundation & Blueprint
- [x] **Project Memory**: Create `task_plan.md`, `findings.md`, `progress.md`, `gemini.md`.
- [ ] **Discovery**: Define user requirements and constraints.
- [ ] **Schema Definition**: Document data structures in `gemini.md`.
- [ ] **Architecture**: Approve technical blueprint (BLAST protocol).

### Phase 2: Core Engineering (Backend - Java)
- [x] **Verification**: Handshake with Ollama (`gemma3:1b`) via script.
- [x] **Setup**: Initialize Java Spring Boot project structure.
- [x] **Integration**: Implement Ollama API client (`OllamaService.java`).
- [x] **Logic**: Create "Template" and JSON enforcement logic (`TestSuite.java`, `TestCase.java`).
- [x] **Endpoint**: Create REST Controller (`TestGenController.java`).

### Phase 3: Interface & Interaction (Frontend)
- [x] **Layer 1**: Define System SOP in `architecture/`.
- [ ] **Setup**: Create `index.html`, `style.css`, `app.js`.
- [ ] **UI**: Build Chat layout (Vanilla CSS).
- [ ] **Integration**: Fetch data from Java backend.
- [ ] **Rendering**: Render JSON response as structured UI.

### Phase 4: Validation & Delivery
- [ ] **Testing**: Verify correctness of generated test cases.
- [ ] **Refinement**: Optimize prompts based on test results.
- [ ] **Documentation**: Create user guide and developer docs.
- [ ] **Final Review**: Ensure all functional requirements are met.

## Goals
- Build a robust, local LLM Testcase generator using Ollama.
- Ensure reliability and determinism as per BLAST protocol.
- Deliver a user-friendly interface for generating test cases.

## Checklists
- [ ] Requirements gathering complete
- [ ] Core engine operational
- [ ] UI fully functional
- [ ] validation successful
